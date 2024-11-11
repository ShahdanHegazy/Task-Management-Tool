import { NgFor } from '@angular/common';
import { Component, Input, input } from '@angular/core';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [
    NgFor,
    RouterLink
  ],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
 @Input()data:any[]=[];
}
