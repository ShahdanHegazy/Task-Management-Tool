import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BoardMember } from '../interfaces/BoardMember';
import { SignedMembersPost } from '../interfaces/SignedMembersPost';

@Injectable({
  providedIn: 'root'
})
export class BoardService {

  constructor(private _HttpClient:HttpClient) { }

  getAllMembers():Observable<BoardMember[]> {
  return this._HttpClient.get<BoardMember[]>('/api/pm/allmembers')
  }
  postSignedMembers(signedMembers:SignedMembersPost):Observable<SignedMembersPost> {
  return this._HttpClient.post<SignedMembersPost>("/api/pm/assign-users",signedMembers)
  }
  getSignedMembers(projectId:number):Observable<any>{
    return this._HttpClient.get<any>(`/api/pm/projects/${projectId}/members`)
  }
}
