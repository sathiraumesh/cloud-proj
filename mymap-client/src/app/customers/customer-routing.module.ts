import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HomeComponent } from "./components/home/home.component";
import { ProductSearchComponent } from "./components/product-search/product-search.component";

const routes: Routes = [{
    path: '',
    component: HomeComponent, children: [
      { path: 'product-search', component: ProductSearchComponent },
      { path: '', redirectTo: 'product-search', pathMatch: 'full' }
    ],
  },
  ];
  
  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
  })
  export class CustomerRoutingModule { }