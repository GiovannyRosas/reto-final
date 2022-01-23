import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateClientComponent } from './components/clients/create-client/create-client.component';
import { EditClientComponent } from './components/clients/edit-client/edit-client.component';
import { ReadClientsComponent } from './components/clients/read-clients/read-clients.component';
import { CreateProductComponent } from './components/products/create-product/create-product.component';

const routes: Routes = [
  { path: '', redirectTo: 'clients', pathMatch: 'full' },
  { path: 'clients', component: ReadClientsComponent },
  { path: 'add-clients', component: CreateClientComponent },
  { path: 'clients/:id/products', component: EditClientComponent },
  { path: 'clients/:id/products/add', component: CreateProductComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
