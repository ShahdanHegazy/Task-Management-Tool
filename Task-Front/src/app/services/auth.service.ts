import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from '../interfaces/login';
import { LoginResponse } from '../interfaces/LoginResponse';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  roleId!:number;
  userId!: number;
  name: string='';

  constructor(private _HttpClient:HttpClient) { }
  login(user:Login): Observable<LoginResponse> {
    return this._HttpClient.post<LoginResponse>('/api/auth/login' ,user);
  }


    setRoleId(roleId:number) {
    this.roleId = roleId;
  }
  setUserId(userId:number) {
    this.userId = userId;
  }
  setName(name:string) {
    this.name = name;
  }
}


