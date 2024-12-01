import { Component, OnInit } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { NavbarComponent } from '../../components/navbar/navbar.component';
import { NgFor, NgIf } from '@angular/common';
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
  sidebarData: SignedProjects[]=[];
  constructor(public router: Router ,private auth:AuthService,private pmService:ProjectmanagerService){}
  ngOnInit(): void {
    this.auth.getuserInformation()
    this.auth.userData.subscribe({
      next:(response)=>{
        if (response?.id !== undefined &&response.roleId==2) {
          this.userId = response.id;
          this.loadSignedProjectsPm(this.userId); 
        } else if(response?.id !== undefined &&response.roleId==3) {
          this.userId = response.id;
          this.loadSignedProjectsMember(this.userId); 
        }
        else console.error('User ID is undefined!');

      },
      error: (err) => console.error(err),
      }
    );
    
  }
  loadSignedProjectsPm(pmId: number): void {
    this.pmService.getSignedProjectsPm(pmId).subscribe({
      next: (response) => {
        console.log(response);
        this.sidebarData = response;
      },
      error: (err) => console.error(err),
    });
  }
  loadSignedProjectsMember(memberId: number): void {
    this.pmService.getSignedProjectsMember(memberId).subscribe({
      next: (response) => {
        console.log(response);
        this.sidebarData = response;
      },
      error: (err) => console.error(err),
    });
  }
  
  logout(){
    localStorage.removeItem('authToken');
    this.router.navigate(['login']);
   }
  }

