import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  private PATH_OF_API = 'http://localhost:8080/api'; // Assuming this is the URL to your backend login endpoint

  constructor(private http: HttpClient) {}

  register(data: any) {
    return this.http.post<any>(this.PATH_OF_API + '/register', data);
  }

  login(email: string, password: string): Observable<any> {
    const body = { email, password };
    console.log(body);
    return this.http.post(this.PATH_OF_API + '/login', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }
}
