import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClientModel } from 'src/app/models/ClientModel';
import { GeneralResponseModel } from 'src/app/models/GeneralResponseModel';
import { ClientsService } from 'src/app/services/clients.service';

@Component({
  selector: 'app-read-clients',
  templateUrl: './read-clients.component.html',
  styleUrls: ['./read-clients.component.css'],
})
export class ReadClientsComponent implements OnInit {
  clients?: ClientModel[];
  currentClient: ClientModel = {};
  currentIndex = -1;

  constructor(private serviceClient: ClientsService, private router: Router) {}

  ngOnInit(): void {
    this.serviceClient.getClients().subscribe({
      next: (res) => {
        if (res.success) {
          this.clients = res.data;
        }
      },
      error: (error) => console.error(error),
    });
  }

  setActiveClient(client: ClientModel): void {
    this.currentClient = client;
  }

  editClient(idClient: number | undefined): void {
    this.router.navigate(['clients/', idClient, 'products']);
  }
}
