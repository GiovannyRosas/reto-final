package com.sophos.backend.services;

import java.util.ArrayList;

import com.sophos.backend.entity.ProductEntity;

public interface ProductService {
  public ArrayList<ProductEntity> getIdProduct(int idClient) throws Exception;

  public ProductEntity findById(int idProduct);

  public String findProductType(Integer id) throws Exception;

  public String findProductState(Integer id) throws Exception;

  public int findIdByNumberAccount(int id) throws Exception;

  public Double findBalance(int id) throws Exception;

  public ProductEntity addProduct(ProductEntity product, int idClient) throws Exception;

  public ProductEntity changeStatus(ProductEntity product);

  public ProductEntity updateBalance(ProductEntity product);

  public ProductEntity cancelProduct(ProductEntity product) throws Exception;

  public void addAmount(int id, Double value) throws Exception;

  public void substractAmmount(int id, Double value) throws Exception;

}
