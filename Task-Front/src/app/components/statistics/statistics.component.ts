import {Component} from '@angular/core';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-statistics',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './statistics.component.html',
  styleUrl: './statistics.component.css'
})
export class StatisticsComponent{
  totalProjects = 20;
  totalMembers= 100;
  totalPMs= 30;
  totalAdmins=3;
}
