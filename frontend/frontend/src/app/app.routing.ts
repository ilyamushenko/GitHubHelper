import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
import {AboutUsComponent} from './about-us/about-us.component';
import { AuthorizationComponent} from './authorization/authorization.component';
import { MainPageComponent} from './main-page/main-page.component';
import { UserPageComponent} from './user-page/user-page.component';
import { AddRepositoryComponent } from './add-repository/add-repository.component';

export const routes: Routes = [
  { path: '', component: MainPageComponent},
  { path: 'authorization', component: AuthorizationComponent },
  { path: 'about-us', component: AboutUsComponent },
  { path: 'user', component: UserPageComponent },
  { path: 'add-repository', component: AddRepositoryComponent}
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
  ],
})
export class AppRoutingModule { }
