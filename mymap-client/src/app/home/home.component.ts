import { Component, OnInit } from '@angular/core';

import { AsgardeoAuthService, Hooks } from "@asgardeo/auth-angular";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  isAuthenticated: boolean = false;

  constructor(private auth: AsgardeoAuthService) {
    auth.on(Hooks.SignIn, () => {
      this.isAuthenticated = true;
    });
    auth.on(Hooks.SignOut, () => {
      this.isAuthenticated = false;
    });
  }

  ngOnInit() {
    this.auth.isAuthenticated().then((payload) => {
      this.isAuthenticated = payload
    });
  }

  handleLogin(): void {
    this.auth.signIn();
  }

  handleLogout(): void {
    this.auth.signOut();
  }

}
