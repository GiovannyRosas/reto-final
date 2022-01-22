package com.sophos.backend.interfaces;

import java.util.ArrayList;

import com.sophos.backend.models.ProductModel;

public interface ProductInterface {
  public ArrayList<ProductModel> getIdProduct(int idClient);
  public ProductModel getIdOneProduct(int idProduct);
  public ProductModel addProduct(ProductModel product, int idClient);
  public ProductModel changeStatus(ProductModel product);
  public ProductModel updateBalance(ProductModel product);
  public ArrayList<ProductModel> listIdOtherAvailableProducts(int idClient, int idProduct);
  public ProductModel cancelProduct(ProductModel product);
  public ProductModel addToBalance(ProductModel product, int movement);
  public ProductModel withdrawToBalance(ProductModel product, int movement);
}
