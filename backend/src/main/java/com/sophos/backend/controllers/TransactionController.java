package com.sophos.backend.controllers;

import java.util.ArrayList;

import com.sophos.backend.interfaces.ProductInterface;
import com.sophos.backend.interfaces.TransactionInterface;
import com.sophos.backend.models.ProductEntity;
import com.sophos.backend.models.TransactionEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clients/{idClient}/products/{idProduct}/transaction")
public class TransactionController {

  @Autowired
  TransactionInterface transactionInterface;

  @Autowired
  ProductInterface productInterface;

  @GetMapping("")
  public ArrayList<TransactionEntity> getTransactionId(@PathVariable("idProduct") int idPrincipalProduct) {
    return transactionInterface.getIdTransaction(idPrincipalProduct);
  }

  // Create a new Transaction
  @PostMapping("")
  @ResponseBody
  public TransactionEntity saveTransaction(@RequestBody TransactionEntity transaction, @PathVariable int idProduct) {
    ProductEntity product = productInterface.getIdOneProduct(idProduct);
    transaction.setIdPrincipalProduct(idProduct);
    if (product.getState().equals("Cancelado")) {/////// Producto cancelado///////
      transaction.setResultOperation("Producto Cancelado");
    } else if (transaction.getTypeOperation().equals("Consignacion")) {/////// Consignacion//////////
      transaction.setFinalBalance(product.getBalance() + transaction.getValueOperation());
      transaction.setResultOperation("Concluido");
      transaction.setFinanceMovement("Credit");
    } else if (transaction.getTypeOperation().equals("Retiro") && product.getState().equals("activa")) {//////// Retiro//////////
      if (product.getTypeAccount().equals("ahorros")
          && product.getBalance() - (1.004 * transaction.getValueOperation()) >= 0) {

        transaction.setFinalBalance(product.getBalance() - (1.004 * transaction.getValueOperation()));
        transaction.setValueOperation(-transaction.getValueOperation());
        transaction.setIdSecondaryProduct(0);
        transaction.setResultOperation("Efectiva");
        transaction.setFinanceMovement("Debito");

      } else if (product.getTypeAccount().equals("corriente")
          && product.getBalance() - (1.004 * transaction.getValueOperation()) >= -200000) {
        transaction.setFinalBalance(product.getBalance() - (1.004 * transaction.getValueOperation()));
        transaction.setValueOperation(-transaction.getValueOperation());
        transaction.setIdSecondaryProduct(0);
        transaction.setResultOperation("Concluido");
        transaction.setFinanceMovement("Debito");
      } else {
        transaction.setResultOperation("Saldo insuficioente, Saldo disponible: " + 0.996 * product.getBalance());
        transaction.setFinalBalance(product.getBalance());
      }
    } else if (transaction.getTypeOperation().equals("transferencia") && product.getState().equals("activa")) {////// Transferencia////////////
      ProductEntity productSend = productInterface.getIdOneProduct(transaction.getIdSecondaryProduct());

      if (product.getTypeAccount().equals("ahorros")
          && product.getBalance() - (1.004 * transaction.getValueOperation()) >= 0) {
        transaction.setFinalBalance(product.getBalance() - (1.004 * transaction.getValueOperation()));
        transaction.setValueOperation(-transaction.getValueOperation());
        transaction.setResultOperation("Concluido");
        transaction.setFinanceMovement("Debito");

        // New Transference
        TransactionEntity transactionSend = new TransactionEntity();

        transactionSend.setIdPrincipalProduct(transaction.getIdSecondaryProduct());
        transactionSend.setValueOperation(-transaction.getValueOperation());
        transactionSend.setFinalBalance(productSend.getBalance() - transaction.getValueOperation());
        transactionSend.setDescription(
            "Transferencia " + transaction.getDescription() + " desde: " + transaction.getIdPrincipalProduct());
        transactionSend.setResultOperation("Concluido");
        transactionSend.setDateOperation(transaction.getDateOperation());
        transactionSend.setTypeOperation("transferencia");
        transactionSend.setIdSecondaryProduct(transaction.getIdPrincipalProduct());
        transactionSend.setFinanceMovement("Credito");
        transactionInterface.createTransaction(transactionSend, transaction.getIdSecondaryProduct());

        productSend.setBalance(transactionSend.getFinalBalance());
        productInterface.updateBalance(productSend);

      } else if (product.getTypeAccount().equals("corriente")
          && product.getBalance() - (1.004 * transaction.getValueOperation()) >= -200000) {
        transaction.setIdSecondaryProduct(transaction.getIdSecondaryProduct());

        transaction.setFinalBalance(product.getBalance() - (1.004 * transaction.getValueOperation()));
        transaction.setValueOperation(-transaction.getValueOperation());
        transaction.setResultOperation("Concluido");
        transaction.setFinanceMovement("Debito");

        // New Transference
        TransactionEntity transactionSend = new TransactionEntity();

        transactionSend.setIdPrincipalProduct(transaction.getIdSecondaryProduct());
        transactionSend.setValueOperation(-transaction.getValueOperation());
        transactionSend.setFinalBalance(productSend.getBalance() - transaction.getValueOperation());
        transactionSend.setDescription(
            "Transferencia " + transaction.getDescription() + " desde: " + transaction.getIdPrincipalProduct());
        transactionSend.setResultOperation("Concluido");
        transactionSend.setDateOperation(transaction.getDateOperation());
        transactionSend.setTypeOperation("transferencia");
        transactionSend.setIdSecondaryProduct(transaction.getIdPrincipalProduct());
        transactionSend.setFinanceMovement("Credito");
        transactionInterface.createTransaction(transactionSend, transaction.getIdSecondaryProduct());

        productSend.setBalance(transactionSend.getFinalBalance());
        productInterface.updateBalance(productSend);

      } else {

        transaction.setResultOperation("Saldo insuficioente, Saldo disponible: " + 0.996 * product.getBalance());
        transaction.setFinalBalance(product.getBalance());
      }

    } else {
      transaction.setResultOperation("Cuenta inactiva");
    }
    product.setBalance(transaction.getFinalBalance());
    productInterface.updateBalance(product);

    return transactionInterface.createTransaction(transaction, idProduct);
  }

}