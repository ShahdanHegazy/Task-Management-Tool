import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BoardMember } from '../interfaces/BoardMember';
import { SignedMembersPost } from '../interfaces/SignedMembersPost';
import { Task } from '../interfaces/Task';

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
  getBoardData(projectId:number):Observable<any>{
    return this._HttpClient.get<any>(`/api/project/${projectId}`)
  }
  createTask(projectId:number,listName:string,task:Task):Observable<any>{
    return this._HttpClient.post<Task>(`/api/pm/projects/${projectId}/${listName}/cards`,task)
  }

  updateTask(taskId:number,task:Task):Observable<any>{
    return this._HttpClient.put<Task>(`/api/pm/cards/${taskId}`,task)
  }
  deleteTask(taskId:number):Observable<any>{
    return this._HttpClient.delete(`/api/pm/cards/${taskId}`)
  }
  moveTask(projectId: number, sourceListName: string, cardId: number, targetListName: string) {
    console.log('projectId:', projectId, 'sourceListName:', sourceListName, 'cardId:', cardId, 'targetListName:', targetListName);
    return this._HttpClient.put(`/api/${projectId}/lists/${sourceListName}/cards/${cardId}/move/${targetListName}`, {});
  }
  
}
