import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { ProductModel } from '../../models/product.model';
@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {
  private productUrl: string = `${environment.api}/products/`;
  
  constructor(private http: HttpClient) {}

  public getProducts(params:any): Observable<ProductModel[]> {
    return this.http.get<ProductModel[]>(`${this.productUrl}`, {params})
  }
}
