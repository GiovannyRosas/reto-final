export class TransactionModel {
  idTransaction?: number;
  idPrincipalProduct?: number;
  idPrincipalClient?: number;
  idSecondaryProduct?: number;
  idSecondaryClient?: number;
  typeOperation?: String;
  valueOperation?: number;
  dateOperation?: String;
  description?: String;
  resultOperation?: String;
  finalBalance?: number;
  GMF?: number;
  financeMovement?: String;
}
