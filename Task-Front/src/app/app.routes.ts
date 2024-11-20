import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { AdminComponent } from './pages/admin/admin.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { UsersComponent } from './components/users/users.component';
import { ProjectsComponent } from './components/projects/projects.component';
import { TaskFormComponent } from './components/task-form/task-form.component';
import { authGuard } from './auth.guard';
import { projectManagerGuard } from './project-manager.guard';
import { adminGuard } from './admin.guard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'project-manager', component: TaskFormComponent ,canActivate:[authGuard,projectManagerGuard]},
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {
    path: "admin", component: AdminComponent,
    canActivate:[authGuard,adminGuard],
    children: [
      { path: "", component: StatisticsComponent },
      { path: "users", component: UsersComponent },
      { path: "projects", component: ProjectsComponent }
    ]
  },
  { path: '**', redirectTo: '/login' },

];
