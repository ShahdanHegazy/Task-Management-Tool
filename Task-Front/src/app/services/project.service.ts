import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProjectManager } from '../interfaces/project-manager';
import { Project } from '../interfaces/project';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private _HttpClient: HttpClient) { }

  getProjectsManagers(): Observable<ProjectManager[]> {
    return this._HttpClient.get<ProjectManager[]>("/api/admin/project-managers")
  }
  createProject(project: Project): Observable<Project> {
    return this._HttpClient.post<Project>("/api/admin/createProject", project)
  } 
  // updateProject(id: number, project: Project): Observable<Project> {
  //   return this._HttpClient.post<Project>("jkjk/{id}", project)
  // }
}
