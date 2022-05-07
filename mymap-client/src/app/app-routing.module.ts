import { NgModule } from '@angular/core';
import { ExtraOptions, RouterModule, Routes } from '@angular/router';
import { HomeComponent } from "./home/home.component";
import { ProductComponent } from "./product/product.component";
import { AsgardeoAuthGuard, AsgardeoSignInRedirectComponent } from "@asgardeo/auth-angular";

export const routes: Routes = [
  {
    path: 'customer',
    loadChildren: () => import('./customers/customers.module')
      .then(m => m.CustomersModule),
  },
  { path: "", component: HomeComponent },
  { path: "product", component: ProductComponent, canActivate: [AsgardeoAuthGuard]},
  {
    path: '**',
    redirectTo: 'customer',
    pathMatch: 'full',
  },
];

const config: ExtraOptions = {
  useHash: false,
};

@NgModule({
  imports: [RouterModule.forRoot(routes, )],
  exports: [RouterModule]
})
export class AppRoutingModule { }
