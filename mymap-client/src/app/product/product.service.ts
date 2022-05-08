import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private productUrl: string = `${environment.api}products`;

  constructor(private httpClient: HttpClient) { }

  // @ts-ignore
  getProductsOfMerchant(merchantId): Observable<any> {
    return this.httpClient.get(`${this.productUrl}/${merchantId}`);
  }

  // @ts-ignore
  update(productId, data): Observable<any> {
    return this.httpClient.patch(`${this.productUrl}/${productId}`, data);
  }
}
