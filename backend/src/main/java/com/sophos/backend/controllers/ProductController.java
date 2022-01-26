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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clients/{idClient}/products")
public class ProductController {

  public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

  @Autowired
  ProductService productInterface;

  @Autowired
  TransactionService transactionInterface;

  // List products owned by the client
  @ApiOperation(value = "List products", response = ResponseEntity.class)
  @GetMapping("")
  public ResponseEntity<GeneralResponse<ArrayList<ProductEntity>>> listIdProduct(
      @PathVariable("idClient") int idClient) {

    GeneralResponse<ArrayList<ProductEntity>> response = new GeneralResponse<>();
    HttpStatus status = null;
    ArrayList<ProductEntity> data = null;

    try {
      data = productInterface.getIdProduct(idClient);
      String msg = "It found " + data.size() + " credit cards.";

      response.setSuccess(true);
      response.setMessage(msg);
      response.setData(data);
      status = HttpStatus.OK;
    } catch (Exception e) {
      String msg = "Something has failed. Please contact suuport.";
      status = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setMessage(msg);
      response.setSuccess(false);

      String log = "End point GET/credit-cards/customer has failed. " + e.getLocalizedMessage();
      logger.error(log);
    }

    return new ResponseEntity<>(response, status);
  }

  // List of products different to the selected one
  @GetMapping("/{idProduct}/different")
  public ArrayList<ProductEntity> listIdOtherAvailableProducts(@PathVariable("idClient") int idClient,
      @PathVariable("idProduct") int idProduct) {
    return productInterface.listIdOtherAvailableProducts(idClient, idProduct);
  }

  // Create a new product for a cliente
  @PostMapping("")
  @ResponseBody
  public ProductEntity save(@RequestBody ProductEntity product, @PathVariable("idClient") int idClient,
      TransactionEntity transaction) {
    product.setIdClient(idClient);

    transaction.setIdPrincipalProduct(product.getIdProduct());
    transaction.setDescription("Creación producto");
    transaction.setResultOperation("Efectiva");
    transaction.setFinalBalance(0);
    transaction.setValueOperation(0);
    transaction.setTypeOperation("Creación cuenta");
    transaction.setDateOperation(transaction.getDateOperation());
    transactionInterface.createTransaction(transaction, product.getIdProduct());
    return productInterface.addProduct(product, idClient);

  }

  // Get one product of a client
  @GetMapping("/{idProduct}")
  public ProductEntity getIdOneProduct(@PathVariable("idProduct") int idProduct) {
    return productInterface.getIdOneProduct(idProduct);
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
    return productInterface.changeStatus(product);
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
    return productInterface.changeStatus(product);
  }

  // Update product balance
  @PutMapping("/{idProduct}")
  public ProductEntity updateBalance(ProductEntity product, @PathVariable("idProduct") int idProduct,
      int finalBalance) {
    product = getIdOneProduct(idProduct);
    product.setBalance(finalBalance);
    return productInterface.updateBalance(product);
  }

  // Add money
  @PutMapping("/{idProduct}/{money}")
  public ProductEntity addMovement(ProductEntity product, @PathVariable("idProduct") int idProduct,
      @PathVariable("money") int money) {
    product = getIdOneProduct(idProduct);
    product.setIdProduct(idProduct);
    product.setIdClient(product.getIdClient());
    product.setTypeAccount(product.getTypeAccount());
    product.setNumberAccount(product.getNumberAccount());
    product.setCreationDate(product.getCreationDate());
    product.setState(product.getState());
    if (product.getBalance() >= 0) {
      product.setBalance(product.getBalance() + money);
    }
    return productInterface.changeStatus(product);
  }

  // Withdraw money
  @PutMapping("/{idProduct}/{money}/withdraw")
  public ProductEntity withdrawMovement(ProductEntity product, @PathVariable("idProduct") int idProduct,
      @PathVariable("money") int money) {
    product = getIdOneProduct(idProduct);
    product.setIdProduct(idProduct);
    product.setIdClient(product.getIdClient());
    product.setTypeAccount(product.getTypeAccount());
    product.setNumberAccount(product.getNumberAccount());
    product.setCreationDate(product.getCreationDate());
    product.setState(product.getState());
    if (money > product.getBalance()) {
      product.setBalance(0);
    } else {
      product.setBalance(product.getBalance() - money);
    }
    return productInterface.changeStatus(product);
  }

}
