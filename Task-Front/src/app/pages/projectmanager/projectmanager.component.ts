import { Component, OnInit } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { NavbarComponent } from '../../components/navbar/navbar.component';
import { NgFor } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { ProjectmanagerService } from '../../services/projectmanager.service';
import { SignedProjects } from '../../interfaces/SignedProject';

@Component({
  selector: 'app-projectmanager',
  standalone: true,
  imports: [NgFor,RouterOutlet, NavbarComponent ,RouterLink],
  templateUrl: './projectmanager.component.html',
  styleUrls: ['./projectmanager.component.css'],
})
export class ProjectmanagerComponent implements OnInit {
  userId!:number;
  constructor(public router: Router ,private auth:AuthService,private pmService:ProjectmanagerService){}
  ngOnInit(): void {
    this.auth.getuserInformation()
    this.auth.userData.subscribe({
      next:(response)=>{
        if (response?.id !== undefined) {
          this.userId = response.id;
          this.loadSignedProjects(this.userId); 
        } else {
          console.error('User ID is undefined!');
        }
      },
      error: (err) => console.error(err),
      }
    );
    
  }
  loadSignedProjects(userId: number): void {
    this.pmService.getSignedProjects(userId).subscribe({
      next: (response) => {
        console.log(response);
        this.sidebarData = response;
      },
      error: (err) => console.error(err),
    });
  }
  sidebarData: SignedProjects[]=[];
  
  logout(){
    localStorage.removeItem('authToken');
    this.router.navigate(['login']);
   }
  }

