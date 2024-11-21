import {Component, OnInit} from '@angular/core';
import {NgClass, NgFor, NgIf, NgStyle} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Project} from '../../interfaces/project';
import {ProjectService} from '../../services/project.service';
import { ProjectManager } from '../../interfaces/project-manager';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
@Component({
  selector: 'app-projects',
  standalone: true,
  imports: [
    NgIf,
    DialogModule,ButtonModule, InputTextModule,
    NgFor,
    NgClass,
    FormsModule,

    
  ],
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {
  mems=['ahmed','ali','sayed','reda','mohamed','ahmed','ali','sayed','reda','mohamed']
  project_id: number | undefined = 0;
  projectName = '';
  projectManagerId: number | null = null;
  start_date: Date | null = null;
  end_date: Date | null = null;
  description = '';
  isModalOpen = false;
  isEditMode = false;
  viewedProject: Project | null = null;
  alertMessage: string = '';
  alertType: 'success' | 'error' = 'success';
  isAlertVisible: boolean = false;
  projects: Project[] = [];
  pms: ProjectManager[] = [];
  members: string[] = ['ahmed','ali'];
  projectManagerName: string | null | undefined;
  visible: boolean=false;

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
        members: this.members
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
confirmDeleteProject(id: number){
  if(confirm(`Are you sure you want to delete this project?`)){
    this.deleteProject(id);
  }
}

  clearForm() {
    this.projectName = '';
    this.projectManagerId = null;
    this.start_date = null;
    this.end_date = null;
    this.description = '';
  }

  showDetails(project: Project) {
    this.viewedProject = project;
    // @ts-ignore
    // this.hoverPosition = {x: event.clientX - 200, y: event.clientY + 10};
  }
  showDialog(){
    this.visible=true;
  }
  
  closeDialog(){
    this.visible=false;
    
  }
  hideDetails() {
    this.viewedProject = null;
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
