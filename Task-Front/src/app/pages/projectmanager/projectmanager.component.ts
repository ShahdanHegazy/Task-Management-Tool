import { Component } from '@angular/core';
import { SidebarComponent } from '../../components/sidebar/sidebar.component';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from '../../components/navbar/navbar.component';

@Component({
  selector: 'app-projectmanager',
  standalone: true,
  imports: [SidebarComponent, RouterOutlet, NavbarComponent],
  templateUrl: './projectmanager.component.html',
  styleUrl: './projectmanager.component.css',
})
export class ProjectmanagerComponent {
    sidebarData: any[] = [
    {
      title: 'Dashboard',
      icon: 'fas fa-dashboard me-2',
      router: '/projectmanager',
    },
    {
      title: 'Project 1',
      icon: 'fas fa-folder me-2',
      router: 'project-1',
    },
  ];

}
