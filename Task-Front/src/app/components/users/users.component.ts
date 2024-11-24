
import { Component, OnInit } from '@angular/core';
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
export class UsersComponent implements OnInit {
  id=0;
  name = '';
  email = '';
  password = '';
  role: Role|null=null;
  isModalOpen = false;
  isEditMode = false;
  alertMessage: string = '';
  alertType: 'success' | 'error' = 'success';
  isAlertVisible: boolean = false;
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
  }

  clearForm() {
    this.name = '';
    this.email = '';
    this.password = '';
    this.role=null;
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
          this.fetchAllUsers()
          this.showAlert('User updated successfully!', 'success');

        },
        error: (error) => {
          console.error('Error updating user:', error);
          this.showAlert('Failed to update user. Please try again.', 'error');
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
          this.fetchAllUsers()
          this.showAlert('User created successfully!', 'success');
        },
        error: (error) => {
          // failure
          console.error('Error:', error);
          this.showAlert('Failed to create user. Please try again.', 'error');
        },
        complete: () => {
          // complete
          console.log('Request completed');
        }
      });
          console.log(newUser);



    }
    this.closeModal();
  }


  deleteUser(duser: User) {
    this._UserService.deleteUserById(duser.id).subscribe({
      next: (response) => {
        console.log(`User with ID ${duser.name} deleted successfully.`);
        console.log("response back is --->"+response);

        this.users = this.users.filter(user => user.id !== duser.id);
        this.showAlert(`User with Name ${duser.name} deleted successfully.`, 'success');
      },
      error: (error) => {console.error("Error deleting user:", error);
        this.showAlert('Failed to delete user. Please try again.', 'error')},
      complete: () => console.log("Delete request completed")
    });
  }

  confirmDeleteUser(duser: User){
    if(confirm(`Are you sure you want to delete user ${duser.name}?`)){
      this.deleteUser(duser);
    }
  }

  showAlert(message: string, type: 'success' | 'error') {
    this.alertMessage = message;
    this.alertType = type;
    this.isAlertVisible = true;

    // إخفاء الـ Alert بعد 2 ثانية
    setTimeout(() => {
      this.isAlertVisible = false;
    }, 2000);
  }
}

