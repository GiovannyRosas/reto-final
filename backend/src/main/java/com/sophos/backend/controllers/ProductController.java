package com.sophos.backend.controllers;

import java.util.ArrayList;

import com.sophos.backend.interfaces.ProductInterface;
import com.sophos.backend.interfaces.TransactionInterface;
import com.sophos.backend.models.ProductModel;
import com.sophos.backend.models.TransactionModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clients/{idClient}/products")
public class ProductController {

  @Autowired
  ProductInterface productInterface;

  @Autowired
  TransactionInterface transactionInterface;

  // List products owned by the client
  @GetMapping("")
  public ArrayList<ProductModel> listIdProduct(@PathVariable("idClient") int idClient) {
    return productInterface.getIdProduct(idClient);
  }

  // List of products different to the selected one
  @GetMapping("/{idProduct}/different")
  public ArrayList<ProductModel> listIdOtherAvailableProducts(@PathVariable("idClient") int idClient,
      @PathVariable("idProduct") int idProduct) {
    return productInterface.listIdOtherAvailableProducts(idClient, idProduct);
  }

  // Create a new product for a cliente
  @PostMapping("")
  @ResponseBody
  public ProductModel save(@RequestBody ProductModel product, @PathVariable("idClient") int idClient,
      TransactionModel transaction) {
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
  public ProductModel getIdOneProduct(@PathVariable("idProduct") int idProduct) {
    return productInterface.getIdOneProduct(idProduct);
  }

  // Change status to active or inactive
  @PutMapping("/{idProduct}/changeStatus")
  public ProductModel changeStatus(ProductModel product, @PathVariable("idProduct") int idProduct) {
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
  public ProductModel cancelProduct(ProductModel product, @PathVariable("idProduct") int idProduct) {
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
  public ProductModel updateBalance(ProductModel product, @PathVariable("idProduct") int idProduct, int finalBalance) {
    product = getIdOneProduct(idProduct);
    product.setBalance(finalBalance);
    return productInterface.updateBalance(product);
  }

  // Add money
  @PutMapping("/{idProduct}/{money}")
  public ProductModel addMovement(ProductModel product, @PathVariable("idProduct") int idProduct,
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
  public ProductModel withdrawMovement(ProductModel product, @PathVariable("idProduct") int idProduct,
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
