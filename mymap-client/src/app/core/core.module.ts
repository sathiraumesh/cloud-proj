import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProductService } from './services/api/product.service';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    HeaderComponent,

  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    NgbModule
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
