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
export class ProjectsComponent implements OnInit {
  project_id: number | undefined = 0;
  projectName = '';
  projectManagerId: number | null = null;
  start_date: Date | null = null;
  end_date: Date | null = null;
  description = '';
  isModalOpen = false;
  isEditMode = false;
  hoveredProject: Project | null = null;
  hoverPosition = {x: 0, y: 0};
  alertMessage: string = '';
  alertType: 'success' | 'error' = 'success';
  isAlertVisible: boolean = false;
  projects: Project[] = [];
  pms: ProjectManager[] = [];
  members: string[] = [];
  projectManagerName: string | null | undefined;

  constructor(private _ProjectService: ProjectService) {}
  

  ngOnInit() {
    this.fetchAllPms();
    this.fetchAllProjects();

  }

  fetchAllPms() {
    this._ProjectService.getProjectsManagers().subscribe({
      next: (response) => {
        this.pms = response
        
        
      },
      error: (err) => {
        console.error("there is wrong something" + JSON.stringify(err, null, 2))
      }
    })
  }
  fetchAllProjects(){
    this._ProjectService.getAllProjects().subscribe({
      next:(data:Project[])=>{
        this.projects=data;
        console.log("fetched all projects"+JSON.stringify(data, null, 2));
      },
      error:(err)=>
        console.log("error fetching projects"+err)
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
      let project: Project = {
        project_id: this.project_id,
        projectName: this.projectName,
        projectManagerId: this.projectManagerId,
        projectManagerName: this.projectManagerName,
        start_date: this.start_date,
        end_date: this.end_date,
        description: this.description,
      };
      this._ProjectService.updateProject(this.project_id,project).subscribe({
        next:(response)=>{
            // this.projects[projectIndex]=response;
            this.showAlert(`Project ${response.projectName} updated successfully`,'success');
            this.fetchAllProjects();
          },
        error:(err)=>console.error(err)
      })
      
    }
    // Create Mood
    else {
      const newProject: Project = {
        projectName: this.projectName,
        projectManagerId: this.projectManagerId,
        start_date: this.start_date,
        end_date: this.end_date,
        description: this.description,
        members: ['ahmed','mock','sa']
      };
      this._ProjectService.createProject(newProject).subscribe({
        next: (response) => {
          console.log(response);
          this.showAlert(`Project ${response.projectName} created successfully`, 'success');
          this.fetchAllProjects();
        },
        error: (err) => {
          console.error(err)
        }
      })
      console.log(newProject);

    }
    this.closeModal();

  }

  deleteProject(id: number) {
    this._ProjectService.deleteProject(id).subscribe({
      next: (response) => {
        const index=this.projects.findIndex(p => p.project_id ===id);
        this.showAlert(`Project with name ${this.projects[index].projectName} deleted successfully`,"success");
        this.fetchAllProjects();
      
    }
  })
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
    this.hoverPosition = {x: event.clientX + 10, y: event.clientY + 10};
  }

  hideDetails() {
    this.hoveredProject = null;
  }
  showAlert(message: string, type: 'success' | 'error') {
    this.alertMessage = message;
    this.alertType = type;
    this.isAlertVisible = true;

    setTimeout(() => {
      this.isAlertVisible = false;
    }, 2000);
  }
}
