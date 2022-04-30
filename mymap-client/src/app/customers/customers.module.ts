import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './components/home/home.component';
import { ProductSearchComponent } from './components/product-search/product-search.component';
import { CustomerRoutingModule } from './customer-routing.module';
import { CoreModule } from '../core/core.module';
import { GoogleMapsModule } from '@angular/google-maps';

@NgModule({
  declarations: [
    HomeComponent,
    ProductSearchComponent
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    CoreModule,
    GoogleMapsModule
  ]
})
export class CustomersModule { }
