import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientModel } from 'src/app/models/ClientModel';
import { ClientsService } from 'src/app/services/clients.service';

@Component({
  selector: 'app-edit-client',
  templateUrl: './edit-client.component.html',
  styleUrls: ['./edit-client.component.css'],
})
export class EditClientComponent implements OnInit {
  client: ClientModel = {
    typeId: '',
    numberId: '',
    lastname: '',
    name: '',
    email: '',
    dateBirth: '',
    telephone: '',
    dateCreation: '',
  };

  constructor(
    private clientsService: ClientsService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      if (params.has('id')) {
        this.clientsService
          .getClientId(params.get('id'))
          .subscribe((data) => (this.client = data));
      }
    });
  }

  getClient(id: String): void {
    this.clientsService.getClientId(id).subscribe({
      next: (data) => {
        this.client = data;
      },
      error: (e) => console.error(e),
    });
  }

  updateClient(): void {
    this.clientsService.updateClient(this.client.id, this.client).subscribe({
      next: (res) => {
        alert(
          'La información del cliente fue actualizada con éxito. Será redirigido a la página de gestión de clientes'
        );
        this.router.navigate(['/clients']);
      },
      error: (e) => console.error(e),
    });
  }

  deleteClient(): void {
    this.clientsService.deleteClient(this.client.id).subscribe({
      next: (res) => {
        this.router.navigate(['/clients']);
      },
      error: (e) => console.error(e),
    });
  }

  returnClient(): void {
    this.router.navigate(['/clients']);
  }
}
