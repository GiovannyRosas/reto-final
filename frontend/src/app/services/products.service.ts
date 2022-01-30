import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GeneralResponseModel } from '../models/GeneralResponseModel';
import { ProductModel } from '../models/ProductModel';

@Injectable({
  providedIn: 'root',
})
export class ProductsService {
  UrlProduct = 'http://localhost:8080/products/';

  constructor(private http: HttpClient) {}

  //List all the products of the client
  getProduct(id: any): Observable<GeneralResponseModel<ProductModel[]>> {
    const url = `${this.UrlProduct}client/${id}`;
    return this.http.get<GeneralResponseModel<ProductModel[]>>(url);
  }

  //List one product
  getOneProduct(id: any, idProduct: any): Observable<ProductModel> {
    const url = `${this.UrlProduct}${id}/products/${idProduct}`;
    return this.http.get<ProductModel>(url);
  }

  //Create a product for a client
  createProduct(data: any, id: any): Observable<any> {
    const url = `${this.UrlProduct}client/${id}`;
    return this.http.post(url, data);
  }

  //Change status to active or inactive
  updateStatusProduct(idProduct: any, data: any): Observable<any> {
    const url = `${this.UrlProduct}${idProduct}/changeStatus`;
    return this.http.put(url, data);
  }

  //Change status to Cancell
  cancelStatusProduct(idProduct: any, data: any): Observable<any> {
    const url = `${this.UrlProduct}${idProduct}/cancel`;
    return this.http.put(url, data);
  }

  //AddMoney
  addMoney(id: any, idProduct: any, money: any, data: any): Observable<any> {
    const url = `${this.UrlProduct}${id}/products/${idProduct}/${money}`;
    return this.http.put(url, data);
  }

  //WithdrawMoney
  withdrawMoney(
    id: any,
    idProduct: any,
    money: any,
    data: any
  ): Observable<any> {
    const url = `${this.UrlProduct}${id}/products/${idProduct}/${money}/withdraw`;
    return this.http.put(url, data);
  }
}
