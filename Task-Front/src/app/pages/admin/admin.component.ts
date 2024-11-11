import { Component } from '@angular/core';
import { SidebarComponent } from '../../components/sidebar/sidebar.component';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from '../../components/navbar/navbar.component';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [SidebarComponent, RouterOutlet, NavbarComponent],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css',
})
export class AdminComponent {
  sidebarData: any[] = [
    {
      title: 'Dashboard',
      icon: 'fas fa-dashboard me-2',
      router: '/admin',
    },
    {
      title: 'Projects',
      icon: 'fas fa-folder me-2',
      router: 'projects',
    },
    { title: 'Users', icon: 'fas fa-users me-4', router: 'users' },
  ];
}
