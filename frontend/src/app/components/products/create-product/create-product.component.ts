import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientModel } from 'src/app/models/ClientModel';
import { ProductModel } from 'src/app/models/ProductModel';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css'],
})
export class CreateProductComponent implements OnInit {
  dateNow = new Date();

  products?: ProductModel[];
  clients?: ClientModel[];

  product: ProductModel = {
    typeAccount: '',
    numberAccount: '',
    creationDate: '',
    state: '',
    balance: 0,
  };

  save = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private productService: ProductsService
  ) {}

  ngOnInit(): void {}

  saveProduct(): void {
    const data = {
      typeAccount: this.product.typeAccount,
      numberAccount: this.product.numberAccount,
      state: 'activa',
      creationDate: formatDate(this.dateNow, 'YYYY-MM-dd', 'en-US'),
    };

    this.route.paramMap.subscribe((params) => {
      if (params.has('id')) {
        this.productService
          .getProduct(params.get('id'))
          .subscribe((res) => (this.products = res.data));
        this.productService.createProduct(data, params.get('id')).subscribe({
          next: () => {
            this.router.navigate(['clients', params.get('id'), 'products']);
          },
        });
      }
    });
  }

  returnProduct(): void {
    this.route.paramMap.subscribe((params) => {
      this.router.navigate(['clients', params.get('id'), 'products']);
    });
  }
}
