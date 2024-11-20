import {Component, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgForOf, NgIf} from '@angular/common';


@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    NgForOf
  ],
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit{
  showTextarea = false;
  newListName = '';
  lists: string[] = [];
  tasks: string[][] = [];
  showTaskInput: boolean[] = [];
  showForm: boolean = false;
  members: string[] = ['mariam', 'Bob', 'Charlie', 'Diana'];
  selectedMembers: string[] = [];



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

  addMember() {

  }
}
