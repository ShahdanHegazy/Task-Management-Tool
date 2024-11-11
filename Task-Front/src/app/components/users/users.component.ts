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
// import { json } from 'stream/consumers';


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
    if (this.isEditMode) {
      const userIndex = this.users.findIndex(user => user.id === this.id);
      if (userIndex !== -1) {
        this.users[userIndex] = {
          id:this.id,
          name: this.name,
          email: this.email,
          password: this.password,
          roleId: this.role
        };
        console.log(this.users[userIndex]);

      }
    } else {
      const newUser: User = {
        name: this.name,
        email: this.email,
        password: this.password,
        roleId: this.role,
        id: 0
      };
          // console.log(newUser);
      this._UserService.createUser(newUser).subscribe({
        next: (response) => {

          // success
          console.log("user created successfully-->:"+response);
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
    this.users = this.users.filter(user => user.id !== duser.id);
  }
  // deleteUser(duser: User) {
  //   this._UserService.deleteUserById(duser.id).subscribe({
  //     next: () => {
  //       console.log(`User with ID ${duser.id} deleted successfully.`);
  //       // حذف المستخدم من الـ array بالـ id
  //       this.users = this.users.filter(user => user.id !== duser.id);
  //     },
  //     error: (error) => console.error("Error deleting user:", error),
  //     complete: () => console.log("Delete request completed")
  //   });
  // }

 showAlert(){
   this.successMsg=true
    setTimeout(()=>{
      this.successMsg=false
    },2000)
 }
}

