import {Component, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgFor, NgForOf, NgIf} from '@angular/common';
import { AuthService } from '../../services/auth.service';


@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  standalone: true,
  imports: [
    NgFor,
    FormsModule,
    NgIf
    ],
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {


constructor(private _AuthService:AuthService){}
  showTextarea = false;
  newListName = '';
  lists: string[] = ['project','project55','project66'];
  tasks: string[][] = [];
  showTaskInput: boolean[] = [];
  showForm: boolean = false;
  members: string[] = ['mariam', 'Bob', 'Charlie', 'Diana'];
  selectedMembers: string[] = [];
  roleId?:number;

  ngOnInit(): void {
    this._AuthService.userData.subscribe(userData => {
      if (userData) {
        this.roleId = userData.roleId;
      }
    });
  }
  
  

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

// Method to remove a list by index

  removeList(index: number): void {
    this.lists.splice(index, 1); // Removes one element at the specified index
  }

  openForm() {
    this.showForm = true;
  }

  closeForm() {
    this.showForm = false;
  }

  onMemberSelected(event: Event) {
    const target = event.target as HTMLSelectElement;
    const selectedMember = target.value;

    if (selectedMember && !this.selectedMembers.includes(selectedMember)) {
      this.selectedMembers.push(selectedMember);
    }
  }

  addMember(): void {
    alert(`Assigned members: ${this.selectedMembers.join(', ')}`);
    this.closeForm();
  }
  
  addTaskToList(listIndex: number, taskName: string): void {
    if (taskName.trim()) {
      this.tasks[listIndex].push(taskName.trim()); // إضافة المهمة في القائمة المطلوبة
      this.showTaskInput[listIndex] = false; // إخفاء إدخال المهمة بعد الإضافة
    }
  }
  
  cancelAddTask(listIndex: number): void {
    this.showTaskInput[listIndex] = false; // إخفاء إدخال المهمة إذا لم يتم الإضافة
  }
  
  removeTask(listIndex: number, taskIndex: number): void {
    this.tasks[listIndex].splice(taskIndex, 1); // حذف المهمة من القائمة
  }
  showSuccessMessage(message: string): void {
    alert(message); // يمكن استبدالها بمنبه أكثر احترافية
  }
  
}
