import { Component, Input, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { ProjectmanagerService } from '../../services/projectmanager.service';
import { SignedProjects } from '../../interfaces/SignedProject';
import { AuthService } from '../../services/auth.service';
import { NgFor } from '@angular/common';


@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  standalone: true,
  imports: [
    RouterLink,
    NgFor
  ],
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  projects: SignedProjects[] = [];
  userId!: number;

  constructor(private router: Router, private _ProjectmanagerService: ProjectmanagerService, private auth: AuthService) { }
  ngOnInit(): void {
    this.auth.userData.subscribe(
      {
        next: (response) => {
          if (response?.id !== undefined && response.roleId == 2) {
            this.userId = response.id;
            this.loadPmProjects();
          } else if (response?.id !== undefined && response.roleId == 3) {
            this.userId = response.id;
            this.loadMemberProjects();
          }
          else {
            console.error('User ID is undefined!');
          }
        },
        error: (err) => console.error(err),
      }
    );
  }
  loadPmProjects() {
    this._ProjectmanagerService.getSignedProjectsPm(this.userId).subscribe({
      next: (response) => {
        this.projects = response;
      },
      error: (err) => console.error(err),
    })
  }
  loadMemberProjects() {
    this._ProjectmanagerService.getSignedProjectsMember(this.userId).subscribe({
      next: (response) => {
        this.projects = response;
      },
      error: (err) => console.error(err),
    })
  }
}

