import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-store-login',
  templateUrl: './store-login.component.html',
  styleUrls: ['./store-login.component.scss']
})
export class StoreLoginComponent implements OnInit {

  constructor(
    private fb: FormBuilder,
    private router: Router
    ) { }

  public merchantIdForm = this.fb.group({
    merchantId:  ['', [Validators.required]]
  }) 

  ngOnInit(): void {
  }

  public submit(): void{
    const {merchantId} = this.merchantIdForm.getRawValue();
    this.router.navigate(["customer/product-search"])
  }



}
