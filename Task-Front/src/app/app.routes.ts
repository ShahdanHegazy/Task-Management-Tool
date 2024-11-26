import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { AdminComponent } from './pages/admin/admin.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { UsersComponent } from './components/users/users.component';
import { ProjectsComponent } from './components/projects/projects.component';
import {ProjectmanagerComponent} from './pages/projectmanager/projectmanager.component';
import { authGuard } from './auth.guard';
import { projectManagerGuard } from './project-manager.guard';
import { adminGuard } from './admin.guard';
import { CardComponent } from './components/card/card.component';
import { BoardComponent } from './components/board/board.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
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
  {path:"projectmanager" ,component:ProjectmanagerComponent ,
   canActivate:[authGuard,projectManagerGuard]
   ,children :[
      {path:"" ,component:CardComponent},
      {path:"project/:id" ,component:BoardComponent},
]},
  { path: '**', redirectTo: '/login' },

];

