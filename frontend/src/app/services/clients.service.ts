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

  createClient(data: any): Observable<any> {
    return this.http.post(this.URIClient, data);
  }
}
