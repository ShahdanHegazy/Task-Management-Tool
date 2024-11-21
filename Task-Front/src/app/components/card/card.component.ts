import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  standalone: true,
  styleUrls: ['./card.component.css']
})
export class CardComponent {
  @Input() title: string = 'Project Name';
  @Input() description: string = 'Default Description';

  constructor(private router: Router) {}

  navigateToPage() {
    // Replace with your desired route or URL

  }
}
