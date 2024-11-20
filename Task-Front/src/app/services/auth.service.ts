import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Login } from '../interfaces/login';
import { LoginResponse } from '../interfaces/LoginResponse';
import { jwtDecode } from 'jwt-decode';
import { DecodedToken } from '../interfaces/DecodedToken';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  roleId!:number;
  userId!: number;
  name: string='';
  userData:BehaviorSubject<DecodedToken|null>=new BehaviorSubject<DecodedToken|null>(null);

  constructor(private _HttpClient:HttpClient) { }
  login(user:Login): Observable<LoginResponse> {
    return this._HttpClient.post<LoginResponse>('api/auth/login' ,user);
  }


  getuserInformation(){
    // if(typeof window !== 'undefined'){
      const token = localStorage.getItem('authToken');
      if (token) {
        this.userData.next(jwtDecode(token));
      }
        else {
          this.userData.next(null);
        }
    }

    // }
}


