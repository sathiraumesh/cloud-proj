import { Component, OnInit } from '@angular/core';

import { AsgardeoAuthService } from "@asgardeo/auth-angular";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  isAuthenticated: boolean | undefined;
  userInfo: any;
  idToken: any;

  constructor(private auth: AsgardeoAuthService) {
  }

  ngOnInit(): void {
    this.auth.isAuthenticated().then((payload) => {
      this.isAuthenticated = payload;
      if (this.isAuthenticated) {
        this.auth.getBasicUserInfo().then((payload) => {

          this.userInfo = payload
        });
        this.auth.getIDToken().then((payload) => this.idToken = this.parseIdToken(payload));
      }
    });
  }

  parseIdToken(idToken: string) {
    if (!idToken) {
      return;
    }

    if (typeof idToken !== "string") {
      idToken = JSON.stringify(idToken);
    }

    const idTokenSplit = idToken.split(".");
    const idTokenObject = {
      encoded: [],
      decoded: []
    };

    idTokenSplit.forEach((element) => {
      // @ts-ignore
      idTokenObject.encoded.push(element);
    });

    // @ts-ignore
    idTokenObject.decoded.push(JSON.parse(atob(idTokenObject.encoded[0])));
    // @ts-ignore
    idTokenObject.decoded.push(JSON.parse(atob(idTokenObject.encoded[1])));

    return idTokenObject;
  }
}
