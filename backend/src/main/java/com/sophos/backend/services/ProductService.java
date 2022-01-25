package com.sophos.backend.services;

import java.util.ArrayList;

import com.sophos.backend.interfaces.ProductInterface;
import com.sophos.backend.interfaces.TransactionInterface;
import com.sophos.backend.models.ProductEntity;
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
  public ArrayList<ProductEntity> getIdProduct(int idClient) {
    return productRepository.findByIdClient(idClient);
  }

  @Override
  public ProductEntity getIdOneProduct(int idProduct) {
    return productRepository.findByIdProduct(idProduct);
  }

  @Override
  public ProductEntity addProduct(ProductEntity product, int idClient) {
    return productRepository.save(product);
  }

  @Override
  public ProductEntity changeStatus(ProductEntity product) {
    return productRepository.save(product);
  }

  @Override
  public ProductEntity updateBalance(ProductEntity product) {
    return productRepository.save(product);
  }

  @Override
  public ArrayList<ProductEntity> listIdOtherAvailableProducts(int idClient, int idProduct) {
    return productRepository.findByStateNotAndIdClientNotAndIdProductNot("Cancelado", idClient, idProduct);
  }

  @Override
  public ProductEntity cancelProduct(ProductEntity product) {
    return productRepository.save(product);
  }

  @Override
  public ProductEntity addToBalance(ProductEntity product, int movement) {
    return productRepository.save(product);
  }

  @Override
  public ProductEntity withdrawToBalance(ProductEntity product, int movement) {
    return productRepository.save(product);
  }

}
