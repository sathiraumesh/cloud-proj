import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProductService } from './services/api/product.service';
import { HttpClientModule } from '@angular/common/http';
import { StoreLoginComponent } from '../customers/components/store-login/store-login.component';



@NgModule({
  declarations: [
    HeaderComponent,
    StoreLoginComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
  ],
  exports: [
    HeaderComponent,
    NgbModule
  ],
  providers: [
    ProductService
  ]
})
export class CoreModule { }
