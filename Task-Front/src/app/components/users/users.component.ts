import { Component } from '@angular/core';
import { NgClass, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

enum Role {
  admin = 1,
  pm = 2,
  member = 3
}

// Interface with numeric role representation
interface User {
  id: number;
  username: string;
  email: string;
  password: string;
  role: Role; // Numeric representation for efficiency and consistency
}

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [
    NgIf,
    NgFor,
    NgClass,
    FormsModule
  ],
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent {
  username = '';
  email = '';
  password = '';
  role!: Role; // Pre-selected role for clarity?
  isModalOpen = false;
  isEditMode = false;
  editUserId: number | null = null;

  users: User[] = [];

  openModal(isEdit: boolean = false, user?: User) {
    this.isModalOpen = true;
    this.isEditMode = isEdit;
    if (isEdit && user) {
      this.editUserId = user.id;
      this.username = user.username;
      this.email = user.email;
      this.password = user.password; // Assuming password editing is allowed
      this.role = user.role;
    } else {
      this.clearForm();
    }
  }

  closeModal() {
    this.isModalOpen = false;
    this.clearForm();
    this.isEditMode = false;
    this.editUserId = null;
  }

  clearForm() {
    this.username = '';
    this.email = '';
    this.password = '';
    // this.role = this.users.length ? this.users[0].role : Role.admin; // Pre-select role if users exist, default to admin
  }

  createOrUpdateUser() {
    if (this.isEditMode && this.editUserId !== null) {
      const userIndex = this.users.findIndex(user => user.id === this.editUserId);
      if (userIndex !== -1) {
        this.users[userIndex] = {
          id: this.editUserId,
          username: this.username,
          email: this.email,
          password: this.password,
          role: this.role
        };
        console.log(this.users[userIndex]);
        
      }
    } else {
      const newUser: User = {
        id: this.users.length + 1,
        username: this.username,
        email: this.email,
        password: this.password,
        role: this.role
      };
      this.users.push(newUser);
      console.log(newUser);
    }
    this.closeModal();
    
  }

  deleteUser(userId: number) {
    this.users = this.users.filter(user => user.id !== userId);
  }
}