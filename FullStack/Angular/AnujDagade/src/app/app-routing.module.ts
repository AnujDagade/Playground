import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MessageComponent } from './message/message.component';
import { AlertComponent } from './alert/alert.component';
import { AddComponent } from './add/add.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: '', redirectTo: '/msg', pathMatch: 'full' },
  { path: 'msg', component: MessageComponent },
  { path: 'alert', component: AlertComponent },
  { path: 'add', component: AddComponent },
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }