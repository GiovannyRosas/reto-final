package com.sophos.backend.repositories;

import java.util.ArrayList;

import com.sophos.backend.models.TransactionEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

  ArrayList<TransactionEntity> findByIdPrincipalProductAndResultOperation(int idPrincipalProduct,
      String resultOperation);

}
