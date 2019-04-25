import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AboutUsComponent} from './about-us/about-us.component';
import {AuthorizationComponent} from './authorization/authorization.component';
import { UserPageComponent } from './user-page/user-page.component';
import {MainPageComponent} from './main-page/main-page.component';

const routes: Routes = [
  { path: '', component: MainPageComponent },
  { path: 'authorization', component: AuthorizationComponent },
  { path: 'user', component: UserPageComponent },
  { path: 'aboutus', component: AboutUsComponent }
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
