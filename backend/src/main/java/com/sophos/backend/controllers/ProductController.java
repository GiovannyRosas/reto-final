package com.sophos.backend.controllers;

import java.util.ArrayList;

import com.sophos.backend.entity.ProductEntity;
import com.sophos.backend.entity.TransactionEntity;
import com.sophos.backend.model.GeneralResponse;
import com.sophos.backend.services.ProductService;
import com.sophos.backend.services.TransactionService;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {

  public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

  @Autowired
  ProductService productService;

  @Autowired
  TransactionService transactionService;

  // List products owned by the client
  @ApiOperation(value = "List products", response = ResponseEntity.class)
  @GetMapping("/client/{idClient}")
  public ResponseEntity<GeneralResponse<ArrayList<ProductEntity>>> listIdProduct(
      @PathVariable("idClient") int idClient) {

    GeneralResponse<ArrayList<ProductEntity>> response = new GeneralResponse<>();
    HttpStatus status = null;
    ArrayList<ProductEntity> data = null;

    try {
      data = productService.getIdProduct(idClient);
      String msg = "It found " + data.size() + " Products.";

      response.setSuccess(true);
      response.setMessage(msg);
      response.setData(data);
      status = HttpStatus.OK;
    } catch (Exception e) {
      String msg = "Something has failed. Please contact support.";
      status = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setMessage(msg);
      response.setSuccess(false);

      String log = "End point GET/products has failed. " + e.getLocalizedMessage();
      logger.error(log);
    }

    return new ResponseEntity<>(response, status);
  }

  // Create a new product for a client
  @PostMapping("/client/{idClient}")
  @ResponseBody
  public ResponseEntity<GeneralResponse<ProductEntity>> saveProduct(@RequestBody ProductEntity product,
      @PathVariable("idClient") int idClient,
      TransactionEntity transaction) {
    GeneralResponse<ProductEntity> response = new GeneralResponse<>();
    HttpStatus status = null;
    ProductEntity data = null;

    try {

      product.setIdClient(idClient);

      transaction.setIdPrincipalProduct(product.getIdProduct());
      transaction.setDescription("Creación producto");
      transaction.setResultOperation("Efectiva");
      transaction.setFinalBalance(0);
      transaction.setValueOperation(0);
      transaction.setTypeOperation("Creación cuenta");
      transaction.setDateOperation(transaction.getDateOperation());
      transactionService.createTransaction(transaction, product.getIdProduct());

      data = productService.addProduct(product, idClient);

      String msg = "It create Products.";

      response.setSuccess(true);
      response.setMessage(msg);
      response.setData(data);
      status = HttpStatus.OK;

    } catch (Exception e) {
      String msg = "Something has failed. Please contact support.";
      status = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setMessage(msg);
      response.setSuccess(false);

      String log = "End point GET/products has failed. " + e.getLocalizedMessage();
      logger.error(log);
    }

    return new ResponseEntity<>(response, status);

  }

  // Get one product of a client
  @GetMapping("/{idProduct}")
  public ProductEntity getIdOneProduct(@PathVariable("idProduct") int idProduct) {
    return productService.findById(idProduct);
  }

  // Change status to active or inactive
  @PutMapping("/{idProduct}/changeStatus")
  public ProductEntity changeStatus(ProductEntity product, @PathVariable("idProduct") int idProduct) {
    product = getIdOneProduct(idProduct);
    product.setIdProduct(idProduct);
    product.setIdClient(product.getIdClient());
    product.setTypeAccount(product.getTypeAccount());
    product.setNumberAccount(product.getNumberAccount());
    product.setCreationDate(product.getCreationDate());
    product.setBalance(product.getBalance());
    if (product.getState().equals("activa")) {
      product.setState("inactiva");
    } else if (product.getState().equals("inactiva")) {
      product.setState("activa");
    } else if (product.getState().equals("Cancelado")) {
      product.setState("Cancelado");
    }
    return productService.changeStatus(product);
  }

  // Change status to cancell product
  @PutMapping("/{idProduct}/cancel")
  public ProductEntity cancelProduct(ProductEntity product, @PathVariable("idProduct") int idProduct) {
    product = getIdOneProduct(idProduct);
    product.setIdProduct(idProduct);
    product.setIdClient(product.getIdClient());
    product.setTypeAccount(product.getTypeAccount());
    product.setNumberAccount(product.getNumberAccount());
    product.setCreationDate(product.getCreationDate());
    product.setBalance(product.getBalance());
    if (product.getBalance() != 0) {
      product.setState(product.getState());
    } else {
      product.setState("Cancelado");
    }
    return productService.changeStatus(product);
  }

  // Update product balance
  @PutMapping("/{idProduct}")
  public ProductEntity updateBalance(ProductEntity product, @PathVariable("idProduct") int idProduct,
      int finalBalance) {

    return productService.updateBalance(product);

  }

  @PostMapping("/{id}/deposit")
  public ResponseEntity<GeneralResponse<TransactionEntity>> deposit(@RequestBody TransactionEntity transaction,
      @PathVariable("id") Integer id) {
    GeneralResponse<TransactionEntity> response = new GeneralResponse<>();
    ProductEntity data = null;
    HttpStatus status = null;
    double balance = 0;
    double value = 0;
    String type = "";
    String msg = "";

    try {
      data = productService.findById(id);

      if (data == null) {
        response.setMessage("Product not found");
      } else {
        value = transaction.getValueOperation();
        balance = data.getBalance();
        type = transaction.getTypeOperation();

        double add = balance + value;

        if (type.equalsIgnoreCase("deposit")) {
          transaction.setValueOperation(add);
          data.setBalance(add);
          transactionService.createTransaction(transaction, id);
          response.setMessage("Deposit successfully completed");
        }
      }

      msg = "Successful transaction";
      response.setMessage(msg);
      response.setSuccess(true);
      response.setData(transaction);
      status = HttpStatus.OK;

    } catch (Exception e) {
      msg = "Something has failed. Please contact suuport.";
      status = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setMessage(msg);
      response.setSuccess(false);
    }

    return new ResponseEntity<>(response, status);
  }

  @PostMapping("/{id}/withdraw")
  public ResponseEntity<GeneralResponse<TransactionEntity>> withdraw(@RequestBody TransactionEntity transaction,
      @PathVariable("id") Integer id) {
    GeneralResponse<TransactionEntity> response = new GeneralResponse<>();
    String state = "";
    ProductEntity data = null;
    HttpStatus status = null;
    String msg = "";
    String type = "";
    double limit = 0;
    double balance = 0;
    double value = 0;

    try {

      data = productService.findById(id);
      if (data == null) {
        response.setMessage("Product no encontrado");
      } else {
        type = productService.findProductType(id);
        if (type.equalsIgnoreCase("Verificando cuenta")) {
          limit = -2000000;
        }

        value = transaction.getValueOperation();
        balance = data.getBalance();
        double substract = balance - value;
        state = productService.findProductState(id);
        if (state.equals("inactiva") || state.equals("Cancelado")) {
          response.setMessage("La cuenta debe se activada");
        } else if (substract < limit) {
          response.setMessage("Insuficiente saldo para continuar");
        } else {
          transaction.setFinalBalance(substract);
          data.setBalance(substract);
          transactionService.createTransaction(transaction, id);
          response.setMessage("Deposito completado");
        }
      }

      msg = "Transaccion satisfactoria";
      response.setMessage(msg);
      response.setSuccess(true);
      response.setData(transaction);
      status = HttpStatus.OK;

    } catch (Exception e) {
      msg = "Something has failed. Please contact suuport.";
      status = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setMessage(msg);
      response.setSuccess(false);
    }

    return new ResponseEntity<>(response, status);

  }

  @PostMapping("/{id}/transfer")
  public ResponseEntity<GeneralResponse<TransactionEntity>> transfer(@RequestBody TransactionEntity transaction,
      @PathVariable("id") Integer id) {
    GeneralResponse<TransactionEntity> response = new GeneralResponse<>();
    String state = "";
    ProductEntity data = null;
    HttpStatus status = null;
    String msg = "";
    String type = "";
    double limit = 0;
    double balance = 0;
    double value = 0;
    int accountNumber = 0;
    int receptor = 0;
    double balanceReceptor = 0;

    try {
      data = productService.findById(id);

      if (data == null) {
        response.setMessage("Producto no encontrado");
      } else {
        type = productService.findProductType(id);
        if (type.equalsIgnoreCase("verificando cuenta")) {
          limit = -2000000;
        }

        accountNumber = transaction.getNumberAccount();
        receptor = productService.findIdByNumberAccount(accountNumber);
        balanceReceptor = productService.findBalance(receptor);
        value = transaction.getValueOperation();
        balance = productService.findBalance(id);

        TransactionEntity auxTransaction;
        ProductEntity productReceptor = new ProductEntity(receptor);

        double add = balanceReceptor + value;
        double substract = balance - value;

        state = productService.findProductState(id);

        if (substract < limit) {
          response.setMessage("Insuficiente saldo");
          response.setSuccess(false);
        } else if (state.equals("inactiva") || state.equals("Cancelado")) {
          response.setSuccess(false);
          response.setMessage("La cuenta debe estar activa");
        } else {
          auxTransaction = new TransactionEntity();

          transaction.setValueOperation(substract);
          auxTransaction.setFinalBalance(add);
          productService.addAmount(id, substract);
          transactionService.createTransaction(transaction, id);
          response.setSuccess(true);
          response.setMessage("Transferencia realizada");

        }

      }

      msg = "Transacción finalizada";
      response.setMessage(msg);
      response.setSuccess(true);
      response.setData(transaction);
      status = HttpStatus.OK;
    } catch (Exception e) {

      msg = "Something has failed. Please contact suuport.";
      status = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setMessage(msg);
      response.setSuccess(false);

    }

    return new ResponseEntity<>(response, status);
  }
}
