package com.sophos.backend.services.impl;

import java.util.ArrayList;

import com.sophos.backend.entity.ProductEntity;
import com.sophos.backend.repositories.ProductRepository;
import com.sophos.backend.services.ProductService;
import com.sophos.backend.services.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  TransactionService transactionInterface;

  @Override
  public ArrayList<ProductEntity> getIdProduct(int idClient) throws Exception {
    return productRepository.findByIdClient(idClient);
  }

  @Override
  public ProductEntity findById(int id) {
    return productRepository.findById(id).orElse(null);
  }

  @Override
  public ProductEntity addProduct(ProductEntity product, int idClient) throws Exception {
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
  public String findProductType(Integer id) throws Exception {
    return productRepository.findProductType(id);
  }

  @Override
  public String findProductState(Integer id) throws Exception {
    return productRepository.findProductState(id);
  }

  @Override
  public int findIdByNumberAccount(int id) throws Exception {
    return productRepository.findIdByAccountNumber(id);
  }

  @Override
  public Double findBalance(int id) throws Exception {
    return productRepository.findBalance(id);
  }

  @Override
  public ProductEntity cancelProduct(ProductEntity product) throws Exception {
    return productRepository.save(product);
  }

  @Override
  public void addAmount(int id, Double value) throws Exception {
    productRepository.addAmmount(id, value);
  }

  @Override
  public void substractAmmount(int id, Double value) throws Exception {
    productRepository.substractAmmount(id, value);
  }

}
