import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ProjectManager} from '../interfaces/project-manager';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private _HttpClient:HttpClient) { }

  getProjectsManagers():Observable<ProjectManager[]>{
    return this._HttpClient.get<ProjectManager[]>("api/admin/project-managers")
  }
}
