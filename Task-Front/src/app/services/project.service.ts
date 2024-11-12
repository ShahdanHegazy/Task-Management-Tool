import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private _HttpClient:HttpClient) { }

  getProjectsManagers():Observable<[]>{
    return this._HttpClient.get<[]>("api/admin/project-managers")
  }
}
