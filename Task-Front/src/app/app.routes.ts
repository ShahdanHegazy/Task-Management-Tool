import { Routes } from '@angular/router';
import {LoginComponent} from './pages/login/login.component';
import {AdminComponent} from './pages/admin/admin.component';
import {StatisticsComponent} from './components/statistics/statistics.component';
import {UsersComponent} from './components/users/users.component';
import {ProjectsComponent} from './components/projects/projects.component';
import {ProjectmanagerComponent} from './pages/projectmanager/projectmanager.component';
import {CardComponent} from './components/card/card.component';
import {ProjectComponent} from './components/project/project.component';



export const routes: Routes = [
  {path:"" ,redirectTo:"login",pathMatch:"full"},
  {path:"login" ,component:LoginComponent},
  {path:"admin" ,component:AdminComponent,children :[
      {path:"" ,component:StatisticsComponent},
      {path:"users",component:UsersComponent},
      {path:"projects",component:ProjectsComponent}
    ]} ,
  {path:"projectmanager" ,component:ProjectmanagerComponent ,children :[
      {path:"" ,component:CardComponent},
      {path:"project" ,component:ProjectComponent},
]},
  ]
