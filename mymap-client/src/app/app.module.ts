import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { AsgardeoAuthModule } from "@asgardeo/auth-angular";
import { HomeComponent } from './home/home.component';
import { ProductComponent } from './product/product.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from "@angular/common";
import { environment } from 'src/environments/environment';
import { GoogleMapsModule } from '@angular/google-maps';

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
      signInRedirectURL: environment.redrict_url,
      signOutRedirectURL: environment.redrict_url,
      clientID: "QShFsljzJkMXUPCMeUhkClJf_KIa",
      baseUrl: "https://api.asgardeo.io/t/techcauldron",
      scope: [ "openid","profile" ]
    }),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    CommonModule,
    GoogleMapsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
