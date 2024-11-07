import { Component } from '@angular/core';
import { NgClass, NgFor, NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';

interface Project {
  id: number;
  name: string;
  pm: string;
  startDate:Date;
  endDate:Date;
}

@Component({
  selector: 'app-projects',
  standalone: true,
  imports: [
    NgIf,
    NgFor,
    NgClass,
    FormsModule
  ],
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent {
  projectName = '';
  projectPM = '';
  projectStartDate :any;
  projectEndDate: any ;
  projects: Project[] = [];
  projectIdCounter = 1;
  isModalOpen = false;
  isEditMode = false;
  editProjectId: number | null = null;
  pms = ['John Doe', 'Jane Smith', 'Ahmed Ali'];
  members=[['ahmed', 'John','khaled','mahmoud'],['salah', 'sayed','shaheen','mahmoud']]

  openModal(isEdit: boolean = false, project?: Project) {
    this.isModalOpen = true;
    this.isEditMode = isEdit;
    if (isEdit && project) {
      this.editProjectId = project.id;
      this.projectName = project.name;
      this.projectPM = project.pm;
      this.projectStartDate=project.startDate;
      this.projectEndDate=project.endDate;
      
    } else {
      this.clearForm();
    }
  }

  closeModal() {
    this.isModalOpen = false;
    this.clearForm();
    this.isEditMode = false;
    this.editProjectId = null;
  }

  createOrUpdateProject() {
    if (this.projectName && this.projectPM) {
      if (this.isEditMode && this.editProjectId !== null) {
        // Update project
        const projectIndex = this.projects.findIndex(p => p.id === this.editProjectId);
        if (projectIndex !== -1) {
          this.projects[projectIndex] = {
            id: this.editProjectId,
            name: this.projectName,
            pm: this.projectPM,
            startDate:this.projectStartDate,
            endDate:this.projectEndDate
          };
        }
      } else {
        // Create new project
        this.projects.push({
          id: this.projectIdCounter++,
          name: this.projectName,
          pm: this.projectPM,
          startDate:this.projectStartDate,
          endDate:this.projectEndDate
        });
      }
      console.log(this.projects[this.projectIdCounter-2]);
      this.closeModal();
    }
    
  }
  //Delete project
  deleteProject(projectId: number) {
    this.projects = this.projects.filter(project => project.id !== projectId);
  }

  clearForm() {
    this.projectName = '';
    this.projectPM = '';
    this.projectStartDate = '';
    this.projectEndDate = '';
  }


}
