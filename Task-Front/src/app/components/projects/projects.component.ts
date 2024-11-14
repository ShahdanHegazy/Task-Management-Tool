import {Component, OnInit} from '@angular/core';
import {NgClass, NgFor, NgIf, NgStyle} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Project} from '../../interfaces/project';
import {ProjectService} from '../../services/project.service';
import { ProjectManager } from '../../interfaces/project-manager';



@Component({
  selector: 'app-projects',
  standalone: true,
  imports: [
    NgIf,
    NgFor,
    NgClass,
    FormsModule,
    NgStyle
  ],
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit{
  project_id: number|undefined = 0;
  projectName = '';
  projectManagerId :number|null=null;
  start_date: Date|null=null;
  end_date: Date|null=null;
  description = '';
  isModalOpen = false;
  isEditMode = false;
  hoveredProject: Project | null = null;
  hoverPosition = { x: 0, y: 0 };
  projects: Project[] = [];
  pms:ProjectManager[] = [];
  members:string[] = [];

  constructor(private _ProjectService:ProjectService) {
  }
  ngOnInit() {
    this.fetchAllPms();
  }

  fetchAllPms(){
    this._ProjectService.getProjectsManagers().subscribe({
      next:(response)=> {this.pms=response},
      error:(err)=>{console.error("there is wrong something"+JSON.stringify(err, null, 2))}
    })
  }
  openModal(isEdit: boolean = false, project?: Project) {
    this.isModalOpen = true;
    this.isEditMode = isEdit;
    if (isEdit && project) {
      this.project_id = project.project_id;
      this.projectName = project.projectName;
      this.projectManagerId = project.projectManagerId;
      this.start_date = project.start_date;
      this.end_date = project.end_date;
      this.description = project.description;
    } else {
      this.clearForm();
    }
  }

  closeModal() {
    this.isModalOpen = false;
    this.clearForm();
    this.isEditMode = false;
    this.project_id = 0;
  }

  createOrUpdateProject() {
    // Update Mood
      if (this.isEditMode) {
          let project:Project = {
            project_id: this.project_id,
            projectName: this.projectName,
            projectManagerId: this.projectManagerId,
            start_date: this.start_date,
            end_date: this.end_date,
            description: this.description,
            members: this.members // Example members
          };
          // this._ProjectService.updateProject(this.id,project).subscribe({
          //   next:(response)=>{
          //     const projectIndex=this.projects.findIndex((project)=>project.id ==response.id)
          //     this.projects[projectIndex]=response;
          //   },
          //   error:(err)=>console.error(err)
          // })

      } 
      // Create Mood
      else {
        const newProject: Project = {
          projectName: this.projectName,
          projectManagerId: this.projectManagerId,
          start_date: this.start_date,
          end_date: this.end_date,
          description: this.description,
        };
        this._ProjectService.createProject(newProject).subscribe({
          next:(response)=>{
            console.log(response);
            
          // this.projects.push(response);
          },
          error:(err)=> {
            console.error(err)
          }
        })
        console.log(newProject);
        
      }
      this.closeModal();

  }

  deleteProject(id: number) {
    this.projects = this.projects.filter(project => project.project_id !== this.project_id);
  }

  clearForm() {
    this.projectName = '';
    this.projectManagerId = null;
    this.start_date = null;
    this.end_date = null;
    this.description = '';
  }

  showDetails(project: Project) {
    this.hoveredProject = project;
    // @ts-ignore
    this.hoverPosition = { x: event.clientX + 10, y: event.clientY + 10 };
  }

  hideDetails() {
    this.hoveredProject = null;
  }
}
