package com.sophos.backend.interfaces;

import java.util.ArrayList;

import com.sophos.backend.models.TransactionEntity;

public interface TransactionInterface {

  public TransactionEntity createTransaction(TransactionEntity transactionModel, int idPrincipalProduct);

  public ArrayList<TransactionEntity> getIdTransaction(int idPrincipalProduct);

}
