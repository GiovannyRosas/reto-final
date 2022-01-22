package com.sophos.backend.repositories;

import java.util.ArrayList;

import com.sophos.backend.models.TransactionModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Integer> {

  ArrayList<TransactionModel> findByIdPrincipalProductAndResultOperation(int idPrincipalProduct, String resultOperation);
  
}
