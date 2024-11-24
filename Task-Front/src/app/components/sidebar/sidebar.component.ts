import { NgFor } from '@angular/common';
import { Component, Input, input } from '@angular/core';
import {Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [
    NgFor,
    RouterLink
  ],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  constructor(private router:Router){}
 @Input()data:any[]=[];
 logout(){
  localStorage.removeItem('authToken');
  this.router.navigate(['login']);
 }
}
