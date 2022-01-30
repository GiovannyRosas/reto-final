import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { CreateProductComponent } from './components/products/create-product/create-product.component';
import { CreateClientComponent } from './components/clients/create-client/create-client.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { ClientsService } from './services/clients.service';
import { FormsModule } from '@angular/forms';
import { ReadClientsComponent } from './components/clients/read-clients/read-clients.component';
import { EditClientComponent } from './components/clients/edit-client/edit-client.component';
import { ReadProductComponent } from './components/products/read-product/read-product.component';
import { TransaccionesComponent } from './components/transacciones/transacciones.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateProductComponent,
    CreateClientComponent,
    NavbarComponent,
    ReadClientsComponent,
    EditClientComponent,
    ReadProductComponent,
    TransaccionesComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [ClientsService],
  bootstrap: [AppComponent],
})
export class AppModule {}
