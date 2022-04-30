import { Component, OnInit } from '@angular/core';
import { ProductModel } from 'src/app/core/models/product.model';
import { ProductSearchService } from 'src/app/core/services/client/product-search.service';

@Component({
  selector: 'app-product-search',
  templateUrl: './product-search.component.html',
  styleUrls: ['./product-search.component.scss']
})
export class ProductSearchComponent implements OnInit {

  public zoom = 30
  public center: any;
  public product: ProductModel | undefined;
  public markers: any = [];

  constructor(private productSearchService$: ProductSearchService) { }

  ngOnInit(): void {
    this.productSearchService$.productAnnounceSubscription.subscribe((data: { product: ProductModel }) => {
      this.product = data.product
    })
    navigator.geolocation.getCurrentPosition((position) => {
      this.center = {
        lat: position.coords.latitude,
        lng: position.coords.longitude,
      }

      this.addMarker()
    })

  }

  addMarker() {
    this.markers.push({
      position: {
        lat: this.center.lat + ((Math.random() - 0.5) * 2) / 10,
        lng: this.center.lng + ((Math.random() - 0.5) * 2) / 10,
      },
      label: {
        color: 'red',
        text: 'Marker label ' + (this.markers.length + 1),
      },
      title: 'Marker title ' + (this.markers.length + 1),
      options: { animation: google.maps.Animation.BOUNCE },
    })
  }
}

