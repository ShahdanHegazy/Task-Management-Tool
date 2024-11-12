// import { Component, OnInit } from '@angular/core';
// import { NgClass, NgFor, NgIf } from '@angular/common';
// import { FormsModule } from '@angular/forms';
// import { Role } from '../../enums/Role';
// import { User } from '../../interfaces/User';
// import { UserService } from '../../services/user.service';
// // import { json } from 'stream/consumers';


// @Component({
//   selector: 'app-users',
//   standalone: true,
//   imports: [
//     NgIf,
//     NgFor,
//     NgClass,
//     FormsModule
//   ],
//   templateUrl: './users.component.html',
//   styleUrls: ['./users.component.css']
// })
// export class UsersComponent implements OnInit {
//   name = '';
//   email = '';
//   password = '';
//   role!: Role;
//   isModalOpen = false;
//   isEditMode = false;
//   editUserEmail: string | null = null;
//   successMsg:boolean=false;
//   constructor(private _UserService: UserService){}

//   ngOnInit(){
//     this.fetchAllUsers();
//   }

//   roleNames = {
//     [Role.admin]: 'Admin',
//     [Role.pm]: 'Project Manager',
//     [Role.member]: 'Member'
//   };
//   users: User[]=[];

//   fetchAllUsers(){
//     this._UserService.getAllUsers().subscribe({
//       next:(data:User[])=>{
//         this.users=data;
//         console.log("fetched all users"+this.users);
//       },
//       error:(err)=>
//         console.log("error fetching users"+err)
//     });
//   }
//   openModal(isEdit: boolean = false, user?: User) {
//     this.isModalOpen = true;
//     this.isEditMode = isEdit;
//     if (isEdit && user) {
//       this.name = user.name;
//       this.email = user.email;
//       this.password = user.password;
//       this.role = user.roleId;
//     } else {
//       this.clearForm();
//     }
//   }

//   closeModal() {
//     this.isModalOpen = false;
//     this.clearForm();
//     this.isEditMode = false;
//     this.editUserEmail = null;
//   }

//   clearForm() {
//     this.name = '';
//     this.email = '';
//     this.password = '';
//   }

//   createOrUpdateUser() {
//     if (this.isEditMode) {
//       const userIndex = this.users.findIndex(user => user.email === this.email);
//       if (userIndex !== -1) {
//         this.users[userIndex] = {
//           name: this.name,
//           email: this.email,
//           password: this.password,
//           roleId: this.role
//         };
//         console.log(this.users[userIndex]);

//       }
//     } else {
//       const newUser: User = {
//         name: this.name,
//         email: this.email,
//         password: this.password,
//         roleId: this.role
//       };
//       this._UserService.createUser(newUser).subscribe({
//         next: (response) => {

//           // success
//           console.log("user created successfully-->:"+response);
//           this.users.push(response);
//           this.showAlert()
//         },
//         error: (error) => {
//           // failure
//           console.error('Error:', error);
//         },
//         complete: () => {
//           // complete
//           console.log('Request completed');
//         }
//       });


//     }
//     this.closeModal();
//   }

//   deleteUser(duser: User) {
//     this.users = this.users.filter(user => user.email !== duser.email);
//   }

//  showAlert(){
//    this.successMsg=true
//     setTimeout(()=>{
//       this.successMsg=false
//     },2000)
//  }
// }

import { Component, OnInit } from '@angular/core';
import { NgClass, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Role } from '../../enums/Role';
import { User } from '../../interfaces/User';
import { UserService } from '../../services/user.service';
import {response} from 'express';

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
export class UsersComponent implements OnInit {
  id=0;
  name = '';
  email = '';
  password = '';
  role!: Role;
  isModalOpen = false;
  isEditMode = false;
  editUserEmail: string | null = null;
  successMsg:boolean=false;
  constructor(private _UserService: UserService){}

  ngOnInit(){
    this.fetchAllUsers();
    console.log(this.users);
  }

  roleNames = {
    [Role.admin]: 'Admin',
    [Role.pm]: 'Project Manager',
    [Role.member]: 'Member'
  };
  users: User[]=[];

  fetchAllUsers(){
    this._UserService.getAllUsers().subscribe({
      next:(data:User[])=>{
        this.users=data;
        console.log("fetched all users"+this.users);
      },
      error:(err)=>
        console.log("error fetching users"+err)
    });
  }
  openModal(isEdit: boolean = false, user?: User) {
    this.isModalOpen = true;
    this.isEditMode = isEdit;
    if (isEdit && user) {
      this.id=user.id;
      this.name = user.name;
      this.email = user.email;
      this.password = user.password;
      this.role = user.roleId;
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
    this.name = '';
    this.email = '';
    this.password = '';
  }

  createOrUpdateUser() {
    // UpdateMode
    let upadatedUser;
    if (this.isEditMode) {
      console.log("before up->"+this.id)
      upadatedUser = {
        id: this.id,
        name: this.name,
        email: this.email,
        password: this.password,
        roleId: this.role
      };
      this._UserService.updateUserById(this.id, upadatedUser).subscribe({
        next: (response) =>{
          console.log("User updated successfully:", response)
          const index = this.users.findIndex(user => user.id === response.id);
          if (index !== -1) {
            this.users[index] = response;  // تحديث المستخدم في الـ array
          }
        },
        error: (error) => {
          console.error('Error updating user:', error);
        },
        complete: () => {
      console.log("after up->"+this.id)
          console.log('Update request completed');
        }
      })


      console.log(upadatedUser);
    }
    // CreateMode
    else {
      const newUser: User = {
        name: this.name,
        email: this.email,
        password: this.password,
        roleId: this.role,
        id: this.id
      };
      this._UserService.createUser(newUser).subscribe({
        next: (response) => {
          // success
          console.log("Response: " + JSON.stringify(response, null, 2));
          this.users.push(response);
          this.showAlert()
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


  deleteUser(duser: User) {
    this._UserService.deleteUserById(duser.id).subscribe({
      next: () => {
        console.log(`User with ID ${duser.id} deleted successfully.`);
        this.users = this.users.filter(user => user.id !== duser.id);
      },
      error: (error) => console.error("Error deleting user:", error),
      complete: () => console.log("Delete request completed")
    });
  }


  showAlert(){
   this.successMsg=true
    setTimeout(()=>{
      this.successMsg=false
    },2000)
 }
}

