import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProjectmanagerService } from '../../services/projectmanager.service';
import { SignedProjects } from '../../interfaces/SignedProject';
import { AuthService } from '../../services/auth.service';
import { NgFor, NgIf } from '@angular/common';


@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  standalone: true,
  imports: [
    NgFor
  ],
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  projects: SignedProjects[] = [];
  userId!: number;

  constructor(private router: Router, private _ProjectmanagerService: ProjectmanagerService, private auth: AuthService) { }
  ngOnInit(): void {
    this.auth.userData.subscribe({
      next: (response) => {
        if (response?.id !== undefined) {
          this.userId = response.id;
        } else {
          console.error('User ID is undefined!');
        }
      },
      error: (err) => console.error(err),
    }
    );
    this._ProjectmanagerService.getSignedProjects(this.userId).subscribe({
      next: (response) => {
        this.projects = response;
      },
      error: (err) => console.error(err),
    })
  }
  navigateToPage() {
    // Replace with your desired route or URL

  }
}
