import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ProductModel } from '../../models/product.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private productUrl: string = `${environment.api}products/list`;
  
  constructor(private http: HttpClient) {}

  public getProducts(params:any): Observable<ProductModel[]> {
    return this.http.get<ProductModel[]>(`${this.productUrl}`, {params})
  }

}
