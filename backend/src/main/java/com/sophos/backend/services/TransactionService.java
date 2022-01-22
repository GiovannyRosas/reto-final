package com.sophos.backend.services;

import java.util.ArrayList;

import com.sophos.backend.interfaces.TransactionInterface;
import com.sophos.backend.models.TransactionModel;
import com.sophos.backend.repositories.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements TransactionInterface{
  
  @Autowired
  private TransactionRepository transactionRepository;

  @Override
  public TransactionModel createTransaction(TransactionModel transaction, int idPrincipalProduct) {
    return transactionRepository.save(transaction);
  }

  @Override
  public ArrayList<TransactionModel> getIdTransaction(int idPrincipalProduct) {
    return transactionRepository.findByIdPrincipalProductAndResultOperation(idPrincipalProduct, "Efectiva");
  }

}
