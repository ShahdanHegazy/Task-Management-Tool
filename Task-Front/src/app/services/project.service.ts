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
  getAllProjects(): Observable<Project[]>{
    return this._HttpClient.get<Project[]>("/api/admin/active/projects")
  }
  updateProject(projectId: number|undefined, project: Project): Observable<Project> {
    return this._HttpClient.put<Project>(`/api/admin/updateProject/${projectId}`, project)
  }

  deleteProject(projectId: number|undefined): Observable<string>{
    return this._HttpClient.delete<string>(`/api/admin/deleteProject/${projectId}`, { responseType: 'text' as 'json' })
  }
  getProjectsCount(): Observable<number> {
    return this._HttpClient.get<number>(`/api/count/project`)
  }
}
