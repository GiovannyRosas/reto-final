import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClientModel } from 'src/app/models/ClientModel';
import { ClientsService } from 'src/app/services/clients.service';

@Component({
  selector: 'app-create-client',
  templateUrl: './create-client.component.html',
  styleUrls: ['./create-client.component.css'],
})
export class CreateClientComponent implements OnInit {
  constructor(private clientService: ClientsService, private router: Router) {}

  dateNow = new Date();

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

  ngOnInit(): void {}

  saveClient(): void {
    const data = {
      typeId: this.client.typeId,
      numberId: this.client.numberId,
      lastname: this.client.lastname,
      name: this.client.name,
      email: this.client.email,
      dateBirth: this.client.dateBirth,
      telephone: this.client.telephone,
      dateCreation: formatDate(this.dateNow, 'YYYY-MM-dd', 'en-US'),
    };

    this.clientService.createClient(data).subscribe({
      next: () => {
        this.router.navigate(['clients']);
      },
      error: (e) => console.error(e),
    });
  }
}
