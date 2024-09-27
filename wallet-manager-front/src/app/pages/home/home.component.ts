import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  isVisibleSubMenu: boolean= false;
  isSidebarClose: boolean= false;

  toggleSubMenu() {
    this.isVisibleSubMenu= !this.isVisibleSubMenu;
  }

  toggleSidebar() {
    this.isSidebarClose= !this.isSidebarClose;
  }
 
}
