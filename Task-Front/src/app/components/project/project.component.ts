import {Component} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
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
  showTextarea = false;
  newListName = '';
  tasks: string[][] = [];
  lists: string[] = [];
  showTaskInput: boolean[] = [];
  showForm: boolean = false;
  members: string[] = ['mariam', 'habiba', 'shahdan', 'Diana'];
  selectedMembers: string[] = [];
  selectedMemberText: string | null = null;
  dropdownOpen: boolean = false;



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
  }

  onMemberSelected(member: string) {
    if (!this.selectedMembers.includes(member)) {
      this.selectedMembers.push(member);
    }
    this.selectedMemberText = member; // Update trigger text
    this.dropdownOpen = false; // Close dropdown after selection
  }


  toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }

  addMember() {

  }


}
