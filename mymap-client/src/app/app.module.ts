import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { AsgardeoAuthModule } from "@asgardeo/auth-angular";
import { HomeComponent } from './home/home.component';
import { ProductComponent } from './product/product.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from "@angular/common";
import { environment } from 'src/environments/environment';

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
      signInRedirectURL: environment.appRedirectUrl,
      signOutRedirectURL: environment.appRedirectUrl,
      clientID: "QShFsljzJkMXUPCMeUhkClJf_KIa",
      baseUrl: "https://api.asgardeo.io/t/techcauldron",
      scope: [ "openid","profile" ]
    }),
    FormsModule,
    HttpClientModule,
    CommonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
