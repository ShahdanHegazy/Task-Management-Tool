import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink,CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  isOpen = false;
  username?:string;
  email?:string;
  constructor(private _AuthService:AuthService){}
 
  ngOnInit(): void {
    this._AuthService.getuserInformation();
    this._AuthService.userData.subscribe((data) => {
      if (data) {
        this.username = data.name;
        this.email=data.sub;
      }
    });
    console.log(this.username);
  }
 
}
