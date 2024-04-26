import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  PATH_OF_API = 'http://localhost:8080/api';


  constructor(
    private http: HttpClient,
    private userAuthService: UserAuthService) { }

    addUser(data: any) {
      return this.http.post<any>(this.PATH_OF_API + '/register', data);
    }

}
