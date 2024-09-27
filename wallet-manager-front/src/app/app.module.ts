import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HomeComponent } from './pages/home/home.component';
import { AccountComponent } from './pages/account/account.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { TransactionComponent } from './pages/transaction/transaction.component';
import { AnalyticsComponent } from './pages/analytics/analytics.component';
import { SettingComponent } from './pages/setting/setting.component';
import { PaymentComponent } from './pages/payment/payment.component';
import { NavbarComponent } from './pages/navbar/navbar.component';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    HomeComponent,
    AccountComponent,
    DashboardComponent,
    TransactionComponent,
    AnalyticsComponent,
    SettingComponent,
    PaymentComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
