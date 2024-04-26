import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-films',
  templateUrl: './films.component.html',
  styleUrls: ['./films.component.css']
})
export class FilmsComponent implements OnInit {
  productRating: number = 0; // Example rating value
  films: any[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<any>('http://localhost:8080/api/films').subscribe(
      (data) => {
        if (data && data._embedded && data._embedded.films) {
          this.films = data._embedded.films;
        } else {
          console.error('Invalid API response:', data);
        }
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );
  }
  
  rateProduct(rating: number) {
    // Logic to update the product's rating
    this.productRating = rating;
    // Here you can also implement logic to send the rating to the server or store it locally.
  }

}
