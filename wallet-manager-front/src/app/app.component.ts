import { Component, inject, OnInit } from '@angular/core';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { fontAwesomeIcons } from './shared/font-awesome-icons';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{

  faIconLibrary= inject(FaIconLibrary);

  ngOnInit(): void {
    this.initFontAwesome();
  }
  
  initFontAwesome() {
    this.faIconLibrary.addIcons(...fontAwesomeIcons);
  }
}
