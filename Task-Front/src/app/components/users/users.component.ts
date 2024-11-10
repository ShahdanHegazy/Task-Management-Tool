import { Component } from '@angular/core';
import { NgClass, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Role } from '../../enums/Role';
import { User } from '../../interfaces/User';
import { UserService } from '../../services/user.service';


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
  role!: Role;
  isModalOpen = false;
  isEditMode = false;
  editUserEmail: string | null = null;
  constructor(private _UserService: UserService){}

  DbUsers: User[] = [
    { email: "mohamedelmanily123@yahoo.com",  password: "dadwada", roleid: 1, username: "mohamedelman" },
    { email: "ahmed@yahoo.com",  password: "dadwada", roleid: 2, username: "ahmed" },
    { email: "ali@yahoo.com", password: "dadwada", roleid: 3, username: "aliali" }
  ];

  roleNames = {
    [Role.admin]: 'Admin',
    [Role.pm]: 'Project Manager',
    [Role.member]: 'Member'
  };
  users: User[] = this.DbUsers;

  openModal(isEdit: boolean = false, user?: User) {
    this.isModalOpen = true;
    this.isEditMode = isEdit;
    if (isEdit && user) {
      this.username = user.username;
      this.email = user.email;
      this.password = user.password;
      this.role = user.roleid;
    } else {
      this.clearForm();
    }
  }

  closeModal() {
    this.isModalOpen = false;
    this.clearForm();
    this.isEditMode = false;
    this.editUserEmail = null;
  }

  clearForm() {
    this.username = '';
    this.email = '';
    this.password = '';
  }

  createOrUpdateUser() {
    if (this.isEditMode) {
      const userIndex = this.users.findIndex(user => user.email === this.email);
      if (userIndex !== -1) {
        this.users[userIndex] = {
          username: this.username,
          email: this.email,
          password: this.password,
          roleid: this.role
        };
        console.log(this.users[userIndex] );

      }
    } else {
      const newUser: User = {
        username: this.username,
        email: this.email,
        password: this.password,
        roleid: this.role
      };
      
      this._UserService.createUser(newUser).subscribe({
        next: (response) => {
          // success
          console.log(response);
          this.users.push(response);
        },
        error: (error) => {
          // failure
          console.error('Error:', error);
        },
        complete: () => {
          // complete
          console.log('Request completed');
        }
      });


    }
    this.closeModal();
  }

  deleteUser(userEmail: string) {
    this.users = this.users.filter(user => user.email !== userEmail);
  }


}
