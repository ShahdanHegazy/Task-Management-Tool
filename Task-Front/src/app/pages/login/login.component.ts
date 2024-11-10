import { Component } from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
loginForm=new FormGroup({
  email:new FormControl(null,[Validators.required,Validators.email]),
  password:new FormControl(null,Validators.required)
})
  isLoading: boolean=false;
  // loginFormData:any;
  submition(){
    this.isLoading=true;
  // this.loginFormData=this.loginForm
    console.log(this.loginForm)

  }


}
