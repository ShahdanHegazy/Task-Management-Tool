import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../interfaces/User';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _HttpClient:HttpClient) { }

  createUser(user: User):Observable<any> {
    return this._HttpClient.post<any>('/api/create', user);
  }
  getAllUsers():Observable<User[]> {
    return this._HttpClient.get<User[]>("/api/all-users-with-roles")
  }
  // deleteUser(user:User):Observable<string>{
  //   return this._HttpClient.delete<string>("/delete",user.email)
  // }
  // deleteUserById(id: number) {
  //   return this.http.delete<User>(`http://localhost:8080/api/users/${id}`);
  // }

}
