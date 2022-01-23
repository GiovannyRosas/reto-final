import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ClientModel } from '../models/ClientModel';

@Injectable({
  providedIn: 'root',
})
export class ClientsService {
  URIClient = 'http://localhost:8080/clients/';

  constructor(private http: HttpClient) {}

  getClients(): Observable<ClientModel[]> {
    return this.http.get<ClientModel[]>(this.URIClient);
  }

  getClientId(id: any): Observable<any> {
    return this.http.get(`${this.URIClient}${id}`);
  }

  createClient(data: any): Observable<any> {
    return this.http.post(this.URIClient, data);
  }

  updateClient(id: any, data: any): Observable<any> {
    return this.http.put(`${this.URIClient}${id}`, data);
  }

  deleteClient(id: any): Observable<any> {
    return this.http.delete(`${this.URIClient}${id}`);
  }
}
