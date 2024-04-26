import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-film-details',
  templateUrl: './film-details.component.html',
  styleUrls: ['./film-details.component.css']
})
export class FilmDetailsComponent implements OnInit {
  productRating: number = 0;
  
  constructor() { }

  ngOnInit(): void {
  }

  rateProduct(rating: number) {
    // Logic to update the product's rating
    this.productRating = rating;
    // Here you can also implement logic to send the rating to the server or store it locally.
  }

}
