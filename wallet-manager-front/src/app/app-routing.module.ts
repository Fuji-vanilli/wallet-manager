import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SidebarComponent } from './sidebar/sidebar.component';
import { HomeComponent } from './pages/home/home.component';
import { AccountComponent } from './pages/account/account.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { TransactionComponent } from './pages/transaction/transaction.component';
import { AnalyticsComponent } from './pages/analytics/analytics.component';
import { SettingComponent } from './pages/setting/setting.component';
import { PaymentComponent } from './pages/payment/payment.component';

const routes: Routes = [
  { path: 'admin', component: HomeComponent, children: [
    { path: 'dashboard', component: DashboardComponent },
    { path: 'account', component: AccountComponent },
    { path: 'transaction', component: TransactionComponent },
    { path: 'payment', component: PaymentComponent },
    { path: 'analytics', component: AnalyticsComponent },
    { path: 'setting', component: SettingComponent }
  ] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
