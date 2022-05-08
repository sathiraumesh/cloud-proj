import { Component, OnInit } from '@angular/core';

import { AsgardeoAuthService } from "@asgardeo/auth-angular";
import { ProductService } from './product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from './product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  isAuthenticated: boolean | undefined;
  userInfo: any;
  idToken: any;
  productsOfMerchant: any;
  message = '';
  submittedProductId: any;
  position: any;

  constructor(private auth: AsgardeoAuthService, private productService: ProductService, private route: ActivatedRoute,
              private router: Router) {
  }

  submitted = false;

  onSubmit() {
    this.submitted = true;
    // @ts-ignore
    this.position = this.productsOfMerchant.map((p) => {
      return p.id;
    }).indexOf(this.submittedProductId);
    this.updateProduct();
  }

  ngOnInit(): void {
    this.auth.isAuthenticated().then((payload) => {
      this.isAuthenticated = payload;
      if (this.isAuthenticated) {
        this.auth.getBasicUserInfo().then((payload) => {
          console.log(payload);
          this.userInfo = payload;
        }).then(() => {
          this.getProductsOfMerchant(this.userInfo.merchantId);
        });
        this.auth.getIDToken().then((payload) => this.idToken = this.parseIdToken(payload));
      }
    });
  }

  // @ts-ignore
  getProductsOfMerchant(merchantId): void {
    // @ts-ignore
    this.productService.getProductsOfMerchant(merchantId)
      .subscribe(
        products => {
          // @ts-ignore
          this.productsOfMerchant = products;
          console.log(products);
        },
        error => {
          console.log(error);
        });
  }
  updateProduct(): void {
    // @ts-ignore
    this.productService.update(this.submittedProductId, this.productsOfMerchant[this.position])
      .subscribe(
        response => {
          console.log(response);
          this.message = 'The product was updated!';
        },
        error => {
          console.log(error);
        });
  }

  parseIdToken(idToken: string) {
    if (!idToken) {
      return;
    }

    if (typeof idToken !== "string") {
      idToken = JSON.stringify(idToken);
    }

    const idTokenSplit = idToken.split(".");
    const idTokenObject = {
      encoded: [],
      decoded: []
    };

    idTokenSplit.forEach((element) => {
      // @ts-ignore
      idTokenObject.encoded.push(element);
    });

    // @ts-ignore
    idTokenObject.decoded.push(JSON.parse(atob(idTokenObject.encoded[0])));
    // @ts-ignore
    idTokenObject.decoded.push(JSON.parse(atob(idTokenObject.encoded[1])));

    return idTokenObject;
  }
}
