import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { ProductModel } from '../../models/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductSearchService {

  private annouceProduct = new Subject<{product:ProductModel}>();

  constructor() { }

  public productAnnounceSubscription = this.annouceProduct.asObservable();

  public announceProduct(product:ProductModel){
    this.annouceProduct.next({product})
  }
}
