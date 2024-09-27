import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  isVisibleSubMenu: boolean= false;

  toggleSubMenu(event: Event) {
    const button= event.target as HTMLElement;
    const submenu= button.nextElementSibling as HTMLElement;

    if (submenu) {
      submenu.classList.toggle('show');
    }
  }

 
}
