package com.sophos.backend.models;

import javax.persistence.*;


@Entity
@Table(name="transactions")
public class TransactionModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idTransaction;
  @JoinColumn(name="idProduct")
  private int idPrincipalProduct;
  private int idSecondaryProduct;
  private String typeOperation;
  private double valueOperation;
  private String dateOperation;
  private String resultOperation;
  private double finalBalance;
  private double GMF;
  private String financeMovement;

  public TransactionModel() {

  }

  public TransactionModel(int idTransaction, int idPrincipalProduct, int idSecondaryProduct, String typeOperation,
      double valueOperation, String dateOperation, String resultOperation, double finalBalance, double gMF,
      String financeMovement) {
    this.idTransaction = idTransaction;
    this.idPrincipalProduct = idPrincipalProduct;
    this.idSecondaryProduct = idSecondaryProduct;
    this.typeOperation = typeOperation;
    this.valueOperation = valueOperation;
    this.dateOperation = dateOperation;
    this.resultOperation = resultOperation;
    this.finalBalance = finalBalance;
    GMF = gMF;
    this.financeMovement = financeMovement;
  }

  public int getIdTransaction() {
    return idTransaction;
  }

  public void setIdTransaction(int idTransaction) {
    this.idTransaction = idTransaction;
  }

  public int getIdPrincipalProduct() {
    return idPrincipalProduct;
  }

  public void setIdPrincipalProduct(int idPrincipalProduct) {
    this.idPrincipalProduct = idPrincipalProduct;
  }

  public int getIdSecondaryProduct() {
    return idSecondaryProduct;
  }

  public void setIdSecondaryProduct(int idSecondaryProduct) {
    this.idSecondaryProduct = idSecondaryProduct;
  }

  public String getTypeOperation() {
    return typeOperation;
  }

  public void setTypeOperation(String typeOperation) {
    this.typeOperation = typeOperation;
  }

  public double getValueOperation() {
    return valueOperation;
  }

  public void setValueOperation(double valueOperation) {
    this.valueOperation = valueOperation;
  }

  public String getDateOperation() {
    return dateOperation;
  }

  public void setDateOperation(String dateOperation) {
    this.dateOperation = dateOperation;
  }

  public String getResultOperation() {
    return resultOperation;
  }

  public void setResultOperation(String resultOperation) {
    this.resultOperation = resultOperation;
  }

  public double getFinalBalance() {
    return finalBalance;
  }

  public void setFinalBalance(double finalBalance) {
    this.finalBalance = finalBalance;
  }

  public double getGMF() {
    return GMF;
  }

  public void setGMF(double gMF) {
    GMF = gMF;
  }

  public String getFinanceMovement() {
    return financeMovement;
  }

  public void setFinanceMovement(String financeMovement) {
    this.financeMovement = financeMovement;
  }

  

}
