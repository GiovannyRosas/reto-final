package com.sophos.backend.repositories;

import java.util.ArrayList;

import com.sophos.backend.models.ProductModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
  
  ArrayList<ProductModel> findByIdClient(int idClient) ;

  ProductModel findByIdProduct(int idProduct);

  @Query(value = "select * from products where (state!=?1) AND NOT (id_client=?2 and id_product=?3)", nativeQuery = true)
  ArrayList<ProductModel> findByStateNotAndIdClientNotAndIdProductNot(String state, int idClient, int idProduct);

}