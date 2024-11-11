import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../interfaces/User';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _HttpClient:HttpClient) { }

  createUser(user: User):Observable<User> {
    return this._HttpClient.post<User>('localhost:8080/api/admin/createUser', user);
  }
}
