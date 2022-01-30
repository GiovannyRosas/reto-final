package com.sophos.backend.repositories;

import java.util.ArrayList;

import javax.transaction.Transactional;

import com.sophos.backend.entity.ProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

  ArrayList<ProductEntity> findByIdClient(int idClient);

  ProductEntity findByIdProduct(int idProduct);

  @Query(value = "select * from products p where (p.state!=?1) AND NOT (p.id_client=?2 and p.id_product=?3)", nativeQuery = true)
  ArrayList<ProductEntity> findByStateNotAndIdClientNotAndIdProductNot(String state, int idClient, int idProduct);

  @Query(value = "select p.state from products p WHERE p.id_product = :id", nativeQuery = true)
  String findProductState(@Param("id") Integer id);

  @Query(value = "select p.type_account from products p WHERE p.id_product= :id", nativeQuery = true)
  String findProductType(@Param("id") Integer id);

  @Query(value = "SELECT p.id_product FROM products p WHERE p.number_account = :account_number", nativeQuery = true)
  Integer findIdByAccountNumber(@Param("account_number") Integer account_number);

  @Query(value = "select p.value_operation from products p where p.id_product = :id", nativeQuery = true)
  Double findBalance(@Param("id") Integer id);

  @Transactional
  @Modifying
  @Query(value = "UPDATE products p SET p.balance = :value WHERE p.id_product = :id", nativeQuery = true)
  void substractAmmount(@Param("id") Integer id, @Param("value") Double value);

  @Transactional
  @Modifying
  @Query(value = "UPDATE products p SET p.balance = :value WHERE p.id_product= :id", nativeQuery = true)
  void addAmmount(@Param("id") Integer id, @Param("value") Double value);
}
