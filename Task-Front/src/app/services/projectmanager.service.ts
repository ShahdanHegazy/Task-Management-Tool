import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { SignedProjects } from '../interfaces/SignedProject';
// import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ProjectmanagerService{

  constructor(private _HttpClient:HttpClient) { }


  getSignedProjects(pmId:number):Observable<SignedProjects[]>{
   return this._HttpClient.get<SignedProjects[]>(`/api/pm/${pmId}`)
  }
  
}
