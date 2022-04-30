import { NgModule } from '@angular/core';
import { ExtraOptions, RouterModule, Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'customer',
    loadChildren: () => import('./customers/customers.module')
      .then(m => m.CustomersModule),
  },
  {
    path: '',
    redirectTo: 'customer',
    pathMatch: 'full',
  },
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
