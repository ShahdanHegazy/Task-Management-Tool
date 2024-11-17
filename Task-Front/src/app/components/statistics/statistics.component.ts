import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { UserService } from '../../services/user.service';
import {count} from 'rxjs';

@Component({
  selector: 'app-statistics',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit{
  totalProjects: number=0;
  totalAdmins: number = 0;
  totalPMs: number = 0;
  totalMembers: number = 0;

  constructor(private _UserService: UserService) {}

  getUsersCount(id:number,targetTotal:"totalAdmins"|"totalPMs"|"totalMembers"){
    this._UserService.getUsersNumber(id).subscribe((count) => {
    this[targetTotal]=count
  })
  }
  ngOnInit() {

   this.getUsersCount(1,"totalAdmins")
   this.getUsersCount(2,"totalPMs")
   this.getUsersCount(3,"totalMembers")
  }
}


