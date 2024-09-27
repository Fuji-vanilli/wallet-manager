import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  isVisibleSubMenu: boolean= false;

  toggleSubMenu() {
    this.isVisibleSubMenu= !this.isVisibleSubMenu;
  }

 
}
