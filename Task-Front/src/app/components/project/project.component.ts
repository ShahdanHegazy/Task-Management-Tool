import {Component} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgForOf, NgIf} from '@angular/common';



@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    NgForOf,
    ReactiveFormsModule
  ],
  styleUrls: ['./project.component.css']
})

export class ProjectComponent {
  taskForm : any
  showTextarea = false;
  newListName = '';
  tasks: string[][] = [];
  lists: string[] = [];
  showTaskInput: boolean[] = [];
  showForm: boolean = false;
  members: string[] = ['mariam', 'habiba', 'shahdan', 'Diana'];
  selectedMembers: string[] = [];
  dropdownOpen: boolean = false;
  createTaskForm = new FormGroup({
    title: new FormControl(null),
    startDate: new FormControl(null),
    endDate: new FormControl(null),
    description: new FormControl(null)
  });
  selectedEmployees: string[] = [];
  employees: string[] = ['Employee 1', 'Employee 2', 'Employee 3', 'Employee 4'];
  isPopupVisible: boolean=false;


  addList() {
    if (this.newListName.trim()) {
      this.lists.push(this.newListName.trim());
      this.tasks.push([]); // Create an empty array for tasks for this new list
      this.showTaskInput.push(false); // Manage task input visibility for the new card
      this.newListName = '';

    }
    this.showTextarea = false;
  }

  cancelAddList() {
    this.showTextarea = false;
    this.newListName = '';
  }

  removeList(index: number): void {
    this.lists.splice(index, 1); // Removes one element at the specified index
  }

  openForm() {
    this.showForm = true;
  }

  closeForm() {
    this.showForm = false;
    this.dropdownOpen = false;
    this.selectedMembers = [];
  }

  toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }

  toggleMemberSelection(member: string) {
    const index = this.selectedMembers.indexOf(member);
    if (index > -1) {
      this.selectedMembers.splice(index, 1);
    } else {
      this.selectedMembers.push(member);
    }
  }

  AddMember() {
    console.log('Selected members:', this.selectedMembers);
    this.closeForm();
  }

  createTask() {
    this.taskForm = {
      ...this.createTaskForm.value,
      assignedEmployees: this.selectedEmployees
    };
    console.log(this.taskForm);
  }

  removeEmployee(employee: string) {
    this.selectedEmployees = this.selectedEmployees.filter(emp => emp !== employee);
  }

  openEmployeeModal() {
    const modal = new (window as any).bootstrap.Modal(document.getElementById('employeeModal'));
    modal.show();
  }

  toggleEmployeeSelection(employee: string) {
    if (this.selectedEmployees.includes(employee)) {
      this.selectedEmployees = this.selectedEmployees.filter(emp => emp !== employee);
    } else {
      this.selectedEmployees.push(employee);
    }
  }

  togglePopup() {
    this.isPopupVisible = !this.isPopupVisible;
  }
}

