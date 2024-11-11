import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-statistics',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent  {
  totalProjects: number=0;
  totalAdmins: number = 0;
  totalPMs: number = 0;
  totalMembers: number = 0;

  // constructor(private _UserService: UserService) {}


  // ngOnInit() {
  //   this._UserService.getUsersNumber(1).subscribe((count) => {
  //     this.totalAdmins = count;
  //   });

  //   this._UserService.getUsersNumber(2).subscribe((count) => {
  //     this.totalPMs = count;
  //   });

  //   this._UserService.getUsersNumber(3).subscribe((count) => {
  //     this.totalMembers = count;
  //   });
  // }
}
