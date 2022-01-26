package com.sophos.backend.services.impl;

import java.util.ArrayList;

import com.sophos.backend.entity.TransactionEntity;
import com.sophos.backend.repositories.TransactionRepository;
import com.sophos.backend.services.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

  @Autowired
  private TransactionRepository transactionRepository;

  @Override
  public TransactionEntity createTransaction(TransactionEntity transaction, int idPrincipalProduct) {
    return transactionRepository.save(transaction);
  }

  @Override
  public ArrayList<TransactionEntity> getIdTransaction(int idPrincipalProduct) {
    return transactionRepository.findByIdPrincipalProductAndResultOperation(idPrincipalProduct, "Concluido");
  }

}
