package com.sophos.backend.services;

import java.util.ArrayList;

import com.sophos.backend.entity.ProductEntity;

public interface ProductService {
  public ArrayList<ProductEntity> getIdProduct(int idClient);

  public ProductEntity getIdOneProduct(int idProduct);

  public ProductEntity addProduct(ProductEntity product, int idClient);

  public ProductEntity changeStatus(ProductEntity product);

  public ProductEntity updateBalance(ProductEntity product);

  public ArrayList<ProductEntity> listIdOtherAvailableProducts(int idClient, int idProduct);

  public ProductEntity cancelProduct(ProductEntity product);

  public ProductEntity addToBalance(ProductEntity product, int movement);

  public ProductEntity withdrawToBalance(ProductEntity product, int movement);
}
