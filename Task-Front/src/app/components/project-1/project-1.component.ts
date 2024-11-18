import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-project',
  templateUrl: './project-1.component.html',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    NgForOf
  ],
  styleUrls: ['./project-1.component.css']
})
export class ProjectComponent {
  showTextarea = false;
  newListName = '';
  lists: string[] = [];
  tasks: string[][] = [];
  showTaskInput: boolean[] = [];
  isModalOpen = false;
  members = ['Alice', 'Bob', 'Charlie', 'Diana']; // Example list of members

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

  openMemberPopup() {
    this.isModalOpen = true
  }

  closeMemberPopup() {
    this.isModalOpen = false;
  }
}
