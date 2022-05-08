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
      if(this.product.lat && this.markers.length<=2){
        this.markers.push({
          position: {
            lat: this.product.lat,
            lng: this.product.lon,
          },
          label: {
            color: 'red',
            text: this.product.name + (this.markers.length + 1),
          },
          title: 'you' + (this.markers.length + 1),
          options: {animation: google.maps.Animation.BOUNCE}
        })
      }
    })
    
    navigator.geolocation.watchPosition((position) => {
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
        lat: this.center.lat,
        lng: this.center.lng,
      },
      label: {
        color: 'red',
        text: 'you' + (this.markers.length + 1),
      },
      title: 'you' + (this.markers.length + 1),
    })
  }

  test(){
    this.center = {
      lat: 0,
      lng: 0
    }
  }
}

