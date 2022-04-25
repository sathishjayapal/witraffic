import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {ITrafficListReaderData} from "./traffic-list-reader-data";
import {TrafficListReaderService} from "./traffic-list-reader-service";

@Component({
  templateUrl: './traffic-list-reader.component.html',
  styleUrls: ['./traffic-list-reader.component.css']
})
export class TrafficListReaderComponent implements OnInit , OnDestroy {
  pageTitle = 'Product List';
  imageWidth = 50;
  imageMargin = 2;
  showImage = false;
  errorMessage = '';
  sub!: Subscription;

  private _listFilter = '';
  get listFilter(): string {
    return this._listFilter;
  }

  set listFilter(value: string) {
    this._listFilter = value;
    this.filteredProducts = this.performFilter(value);
  }

  filteredProducts: ITrafficListReaderData[] = [];
  trafficListReaderData: ITrafficListReaderData[] = [];

  constructor(private trafficListReaderService: TrafficListReaderService) {
  }

  performFilter(filterBy: string): ITrafficListReaderData[] {
    filterBy = filterBy.toLocaleLowerCase();
    return this.trafficListReaderData.filter((product: ITrafficListReaderData) =>
      product.description.toLocaleLowerCase().includes(filterBy));
  }

  toggleImage(): void {
    this.showImage = !this.showImage;
  }

  ngOnInit(): void {
    this.sub = this.trafficListReaderService.getTrafficReaderList().subscribe({
      next: products => {
        this.trafficListReaderData = products;
        this.filteredProducts = this.trafficListReaderData;
      },
      error: err => this.errorMessage = err
    });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  onRatingClicked(message: string): void {
    this.pageTitle = 'Traffic for today: ' + message;
  }
}
