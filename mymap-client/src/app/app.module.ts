import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { AsgardeoAuthModule } from "@asgardeo/auth-angular";
import { HomeComponent } from './home/home.component';
import { ProductComponent } from './product/product.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProductComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule,
    AsgardeoAuthModule.forRoot({
      signInRedirectURL: "http://localhost:4200",
      signOutRedirectURL: "http://localhost:4200",
      clientID: "QShFsljzJkMXUPCMeUhkClJf_KIa",
      baseUrl: "https://api.asgardeo.io/t/techcauldron",
      scope: [ "openid","profile" ]
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
