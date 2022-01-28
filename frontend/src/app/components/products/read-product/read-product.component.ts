import { Component, OnInit, ɵɵsetComponentScope } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductModel } from 'src/app/models/ProductModel';
import { ClientsService } from 'src/app/services/clients.service';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-read-product',
  templateUrl: './read-product.component.html',
  styleUrls: ['./read-product.component.css'],
})
export class ReadProductComponent implements OnInit {
  products?: ProductModel[];
  currentProduct: ProductModel = {};
  currentIndex = -1;

  constructor(
    private productService: ProductsService,
    private clientService: ClientsService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe({
      next: (params) => {
        if (params.has('id')) {
          this.productService
            .getProduct(params.get('id'))
            .subscribe((res) => (this.products = res.data));
        }
      },
      error: (error) => {
        console.error(error);
      },
    });
  }

  addProduct(): void {
    this.route.paramMap.subscribe({
      next: (params) => {
        if (params.has('id')) {
          this.router.navigate([
            'clients/',
            params.get('id'),
            'products',
            'add',
          ]);
        }
      },
      error: (error) => console.log(error),
    });
  }

  updateProduct(idClient: any, idProduct: any): void {
    this.productService
      .updateStatusProduct(idClient, idProduct, this.currentProduct)
      .subscribe({
        next: (res) => {
          alert('El estado del producto fue actualizado con éxito');
          this.route.paramMap.subscribe((params) => {
            this.productService
              .getProduct(params.get('id'))
              .subscribe((res) => (this.products = res.data));
          });
          this.route.paramMap.subscribe((params) => {
            this.router.navigate(['clients/', params.get('id'), 'products']);
          });
        },
        error: (e) => console.error(e),
      });
    this.route.paramMap.subscribe({
      next: (params) => {
        this.router.navigate(['clients/']);
        if (params.has('id')) {
          this.router.navigate(['clients/', params.get('id'), 'products']);
        }
      },
      error: (error) => console.log(error),
    });
  }

  cancelProduct(idClient: any, idProduct: any): void {
    this.productService
      .cancelStatusProduct(idClient, idProduct, this.currentProduct)
      .subscribe({
        next: (res) => {
          alert(
            'Actualización realizada. Si su saldo no es igual a cero su producto no podrá ser cancelado'
          );
          this.route.paramMap.subscribe((params) => {
            this.productService
              .getProduct(params.get('id'))
              .subscribe((resp) => (this.products = resp.data));
          });
          this.route.paramMap.subscribe((params) => {
            this.router.navigate(['clients/', params.get('id'), 'products']);
            this.router.routeReuseStrategy.shouldReuseRoute = function () {
              return false;
            };
          });
        },
        error: (e) => console.error(e),
      });
  }
}
