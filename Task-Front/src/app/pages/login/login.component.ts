import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { jwtDecode } from 'jwt-decode';
import { Router } from '@angular/router';
import { Login } from '../../interfaces/login';
import { DecodedToken } from '../../interfaces/DecodedToken';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  constructor(private _auth: AuthService, private _Router: Router) { }
  loginForm = new FormGroup({
    email: new FormControl(null, [Validators.required, Validators.email]),
    password: new FormControl(null, [Validators.required, Validators.minLength(5)])
  })
  failed: boolean = false;
  success: boolean = false;
  isLoading: boolean = false;

  submition() {
    this.isLoading = true;
    const loginData: Login = {
      email: this.loginForm.get('email')?.value || '',
      password: this.loginForm.get('password')?.value || ''
    };
    this._auth.login(loginData).subscribe({
      next: (response) => {
        localStorage.setItem('authToken', response.token)
        const decodedToken: DecodedToken = jwtDecode(response.token)
        if (decodedToken.roleId == 1) {
          this._Router.navigate(['/admin']);
        } else if (decodedToken.roleId == 2) {
          this._Router.navigate(['/projectmanager']);
        }
        else if (decodedToken.roleId == 3) {
          this._Router.navigate(['/projectmanager']);
        }
        else {
          this._Router.navigate(['/login']);
          console.log(response);
        }
        this.success = true;
      },
      error: (error) => {
        console.error(error);
        this.isLoading = false;
        this.failed = true;
      },
      complete: () => {
        this.isLoading = false;
      }
    });
  }

}

