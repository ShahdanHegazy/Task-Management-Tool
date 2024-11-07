
import { Component } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { NgClass, NgFor } from "@angular/common";

interface User {
  id: number;
  name: string;
  email: string;
  role: string;
  password: string;
}

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
  standalone: true,
  imports: [
    NgFor,
    NgClass,
    FormsModule
  ]
})
export class UsersComponent {
  isModalOpen = false;
  isEditMode = false;
  userName: string = '';
  userEmail: string = '';
  userPassword: string = '';
  userRole: string = '';
  roles = ['Admin', 'Project manager', 'member']; // Example roles

  users: User[] = [
    { id: 1, name: 'John Doe', email: 'john@example.com', role: 'Admin', password: 'password123' },
    { id: 2, name: 'Mariam Ahmed', email: 'mariam@example.com', role: 'Project manager', password: 'password456' }
  ];

  selectedUser: User | null = null;

  openModal(editMode: boolean = false, user: User | null = null) {
    this.isEditMode = editMode;
    this.isModalOpen = true;

    if (editMode && user) {
      this.selectedUser = user;
      this.userName = user.name;
      this.userEmail = user.email;
      this.userPassword = user.password;
      this.userRole = user.role;

    } else {
      this.userName = '';
      this.userEmail = '';
      this.userPassword = '';
      this.userRole = '';
    }
  }

  closeModal() {
    this.isModalOpen = false;
    this.isEditMode = false;
    this.selectedUser = null;
    this.userName = '';
    this.userEmail = '';
    this.userPassword = '';
    this.userRole = '';

  }

  createOrUpdateUser() {
    if (this.isEditMode && this.selectedUser) {
      // Update user
      this.selectedUser.name = this.userName;
      this.selectedUser.email = this.userEmail;
      this.selectedUser.password = this.userPassword;
      this.selectedUser.role = this.userRole;
    } else {
      // Create new user
      const newUser: User = {
        id: this.users.length + 1, // Simplified ID assignment
        name: this.userName,
        email: this.userEmail,
        password: this.userPassword,
        role: this.userRole
      };
      this.users.push(newUser);
    }
    this.closeModal();
  }

  deleteUser(userId: number) {
    this.users = this.users.filter(user => user.id !== userId);
  }
}
