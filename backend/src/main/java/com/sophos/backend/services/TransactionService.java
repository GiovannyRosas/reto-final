package com.sophos.backend.services;

import java.util.ArrayList;

import com.sophos.backend.models.TransactionEntity;

public interface TransactionService {

  public TransactionEntity createTransaction(TransactionEntity transactionModel, int idPrincipalProduct);

  public ArrayList<TransactionEntity> getIdTransaction(int idPrincipalProduct);

}
