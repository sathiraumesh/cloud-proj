import { Component, OnInit } from '@angular/core';
import { ProductSearchService } from 'src/app/core/services/client/product-search.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private productSearchService$: ProductSearchService) { }

  ngOnInit(): void {
    this.productSearchService$.productAnnounceSubscription.subscribe(data => {
    })
  }

}
