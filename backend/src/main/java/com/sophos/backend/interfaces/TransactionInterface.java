package com.sophos.backend.interfaces;

import java.util.ArrayList;

import com.sophos.backend.models.TransactionModel;


public interface TransactionInterface {
  
  public TransactionModel createTransaction(TransactionModel transactionModel, int idPrincipalProduct);

  public ArrayList<TransactionModel> getIdTransaction(int idPrincipalProduct);

}
