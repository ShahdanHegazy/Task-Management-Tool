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
    return this._HttpClient.post<User>('/api/create', user);
  }
  getAllUsers():Observable<User[]> {
    return this._HttpClient.get<User[]>("/api/all-users-with-roles")
  }
  // deleteUser(user:User):Observable<string>{
  //   return this._HttpClient.delete<string>("/delete",user.email)
  // }
  deleteUserById(id: number) {
    return this._HttpClient.delete<User>(`/api/users/${id}`);
  }
  // getUsersNumber(id:number):Observable<number>{
  //   return this._HttpClient.get<number>(`/count-by-role-id/${id}`)
  // }

}
