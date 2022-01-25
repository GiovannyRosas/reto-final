package com.sophos.backend.services.impl;

import java.util.ArrayList;

import com.sophos.backend.models.ProductEntity;
import com.sophos.backend.repositories.ProductRepository;
import com.sophos.backend.services.ProductService;
import com.sophos.backend.services.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  TransactionService transactionInterface;

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
