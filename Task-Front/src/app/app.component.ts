import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {LoginComponent} from './pages/login/login.component';
import {TaskFormComponent} from './components/task-form/task-form.component';
import {SidebarComponent} from './components/sidebar/sidebar.component';
import {AdminComponent} from './pages/admin/admin.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, LoginComponent, TaskFormComponent, SidebarComponent, AdminComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Task-Front';
}
