import { Component } from '@angular/core';
import {NgClass, NgFor, NgIf, NgStyle} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Project} from '../../interfaces/project';



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
export class ProjectsComponent {
  projectName = '';
  projectPM = '';
  projectStartDate: any;
  projectEndDate: any;
  projectDescription = '';
  projectIdCounter = 0;
  isModalOpen = false;
  isEditMode = false;
  editProjectId: number | null = null;
  projects: Project[] = [];
  hoveredProject: Project | null = null;
  hoverPosition = { x: 0, y: 0 };
  pms = ['John Doe', 'Jane Smith', 'Ahmed Ali'];

  openModal(isEdit: boolean = false, project?: Project) {
    this.isModalOpen = true;
    this.isEditMode = isEdit;
    if (isEdit && project) {
      this.editProjectId = project.id;
      this.projectName = project.name;
      this.projectPM = project.pm;
      this.projectStartDate = project.startDate;
      this.projectEndDate = project.endDate;
      this.projectDescription = project.description;
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
        const projectIndex = this.projects.findIndex(p => p.id === this.editProjectId);
        if (projectIndex !== -1) {
          this.projects[projectIndex] = {
            id: this.editProjectId,
            name: this.projectName,
            pm: this.projectPM,
            startDate: this.projectStartDate,
            endDate: this.projectEndDate,
            description: this.projectDescription,
            members: ['Ahmed', 'John', 'Khaled'] // Example members
          };
        }
      } else {
        const newProject: Project = {
          id: this.projectIdCounter++,
          name: this.projectName,
          pm: this.projectPM,
          startDate: this.projectStartDate,
          endDate: this.projectEndDate,
          description: this.projectDescription,
          members: ['Ahmed', 'John', 'Khaled']
        };
        this.projects.push(newProject);
      }
      this.closeModal();
    }
  }

  deleteProject(projectId: number) {
    this.projects = this.projects.filter(project => project.id !== projectId);
  }

  clearForm() {
    this.projectName = '';
    this.projectPM = '';
    this.projectStartDate = '';
    this.projectEndDate = '';
    this.projectDescription = '';
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
