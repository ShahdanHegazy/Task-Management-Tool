import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { AdminComponent } from './pages/admin/admin.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { UsersComponent } from './components/users/users.component';
import { ProjectsComponent } from './components/projects/projects.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {
    path: "admin", component: AdminComponent,
    children: [
      { path: "", component: StatisticsComponent },
      { path: "users", component: UsersComponent },
      { path: "projects", component: ProjectsComponent }
    ]
  },
  { path: '**', redirectTo: '/login' },

];
