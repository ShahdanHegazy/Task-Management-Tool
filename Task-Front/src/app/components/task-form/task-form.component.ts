import { Component } from '@angular/core';
import { NgFor} from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-task-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgFor
  ],
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.css']
})
export class TaskFormComponent {

  taskForm: any;
  employees: string[] = ['Employee 1', 'Employee 2', 'Employee 3', 'Employee 4'];
  selectedEmployees: string[] = [];

  createTaskForm = new FormGroup({
    title: new FormControl(null),
    startDate: new FormControl(null),
    endDate: new FormControl(null),
    description: new FormControl(null)
  });

  createTask() {
    this.taskForm = {
      ...this.createTaskForm.value,
      assignedEmployees: this.selectedEmployees
    };
    console.log(this.taskForm);
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

  removeEmployee(employee: string) {
    this.selectedEmployees = this.selectedEmployees.filter(emp => emp !== employee);
  }
}
