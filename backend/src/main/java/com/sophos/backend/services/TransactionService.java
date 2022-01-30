package com.sophos.backend.services;

import java.util.ArrayList;

import com.sophos.backend.entity.TransactionEntity;

public interface TransactionService {

  public TransactionEntity createTransaction(TransactionEntity transactionModel, int idPrincipalProduct);

}
