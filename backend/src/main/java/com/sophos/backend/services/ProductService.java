package com.sophos.backend.services;

import java.util.ArrayList;

import com.sophos.backend.interfaces.ProductInterface;
import com.sophos.backend.interfaces.TransactionInterface;
import com.sophos.backend.models.ProductModel;
import com.sophos.backend.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements ProductInterface {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  TransactionInterface transactionInterface;

  @Override
  public ArrayList<ProductModel> getIdProduct(int idClient) {
    return productRepository.findByIdClient(idClient);
  }

  @Override
  public ProductModel getIdOneProduct(int idProduct) {
    return productRepository.findByIdProduct(idProduct);
  }

  @Override
  public ProductModel addProduct(ProductModel product, int idClient) {
    return productRepository.save(product);
  }

  @Override
  public ProductModel changeStatus(ProductModel product) {
    return productRepository.save(product);
  }

  @Override
  public ProductModel updateBalance(ProductModel product) {
    return productRepository.save(product);
  }

  @Override
  public ArrayList<ProductModel> listIdOtherAvailableProducts(int idClient, int idProduct) {
    return productRepository.findByStateNotAndIdClientNotAndIdProductNot("Cancelado", idClient, idProduct);
  }

  @Override
  public ProductModel cancelProduct(ProductModel product) {
    return productRepository.save(product);
  }

  @Override
  public ProductModel addToBalance(ProductModel product, int movement) {
    return productRepository.save(product);
  }

  @Override
  public ProductModel withdrawToBalance(ProductModel product, int movement) {
    return productRepository.save(product);
  }

}
