import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SidebarComponent } from './sidebar/sidebar.component';
import { HomeComponent } from './pages/home/home.component';
import { AccountComponent } from './pages/account/account.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';

const routes: Routes = [
  { path: 'admin', component: HomeComponent, children: [
    { path: 'dashboard', component: DashboardComponent },
    { path: 'account', component: AccountComponent }
  ] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
