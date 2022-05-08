import { Component, OnInit, ViewChild } from '@angular/core';
import { NgbTypeahead } from '@ng-bootstrap/ng-bootstrap';
import { Subject, OperatorFunction, Observable, debounceTime, distinctUntilChanged, filter, merge, map, tap } from 'rxjs';
import { ProductModel } from '../../models/product.model';
import { ProductService } from '../../services/api/product.service';
import { ProductSearchService } from '../../services/client/product-search.service';

const states = ['Alabama', 'Alaska', 'American Samoa', 'Arizona', 'Arkansas', 'California', 'Colorado',
  'Connecticut', 'Delaware', 'District Of Columbia', 'Federated States Of Micronesia', 'Florida', 'Georgia',
  'Guam', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine',
  'Marshall Islands', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana',
  'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico', 'New York', 'North Carolina', 'North Dakota',
  'Northern Mariana Islands', 'Ohio', 'Oklahoma', 'Oregon', 'Palau', 'Pennsylvania', 'Puerto Rico', 'Rhode Island',
  'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virgin Islands', 'Virginia',
  'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'];


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  public searchTerm: string = '';

  public products: ProductModel[] = [];
  model: any;

  @ViewChild('instance', { static: true }) instance!: NgbTypeahead
  focus$ = new Subject<string>();
  click$ = new Subject<string>();

  search: OperatorFunction<string, readonly ProductModel[]> = (text$: Observable<string>) => {

    const debouncedText$ = text$.pipe(debounceTime(200), distinctUntilChanged(), tap(text => {
      this.getProducts(text)
    }));
    const clicksWithClosedPopup$ = this.click$.pipe(tap(t => {
    }), filter(() => !this.instance.isPopupOpen()));
    const inputFocus$ = this.focus$;


    return merge(debouncedText$, inputFocus$, clicksWithClosedPopup$).pipe(
      map(term => (term === '' ? this.products
        : this.products.filter(p => p.name.toLocaleLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
    );
  }

  public formatter = (p: ProductModel) => p.name;

  constructor(private productService: ProductService, private productSearchService$: ProductSearchService) { }

  ngOnInit(): void {

  }

  public selectItem(event: any) {
    this.productSearchService$.announceProduct(event.item)
  }

  private getProducts(searchTermString: string) {
    return this.productService.getProducts({ name: searchTermString }).subscribe((products: ProductModel[]) => {
      this.products = products
    });
  }
}
