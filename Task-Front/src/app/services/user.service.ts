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
    return this._HttpClient.post<User>('/api/admin/createUser', user);
  }
  getAllUsers():Observable<User[]> {
    return this._HttpClient.get<User[]>("/api/admin/active/users")
  }
  deleteUserById(id:number):Observable<string>{
    return this._HttpClient.delete<string>(`/api/admin/delete/${id}`, { responseType: 'text' as 'json' })
  }

  updateUserById(id:number,user:User):Observable<User>{
    return this._HttpClient.put<User>(`/api/admin/update/${id}`,user)
  }

  getUsersNumber(id:number):Observable<number>{
    return this._HttpClient.get<number>(`/api/count-by-role-id/${id}`)
  }


}
