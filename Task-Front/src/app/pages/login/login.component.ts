import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { jwtDecode } from 'jwt-decode';
import { Router } from '@angular/router';
import { LoginResponse } from '../../interfaces/LoginResponse';

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
  constructor(private _auth: AuthService, private _Router: Router) { }
  loginForm = new FormGroup({
    email: new FormControl<string | null>(null, [Validators.required, Validators.email]),
    password: new FormControl<string | null>(null, [Validators.required, Validators.minLength(5)])
  })
  isLoading: boolean = false;

  submition() {
    this.isLoading = true;
    //  console.log(
    //    this.loginForm.value
    //  );
    const { email, password } = this.loginForm.value;

    if (email && password) {

      console.log(this.loginForm.value);
      this._auth.login({ email, password }).subscribe({

        next: (response: LoginResponse) => {
          console.log(response.message);
        },
        error: (error) => {
          console.error(error);
          this.isLoading = false;
        },
        complete: () => {
          this.isLoading = false;
        }
      });
    }
     else {
      this.isLoading = false;
    }

  }
}

