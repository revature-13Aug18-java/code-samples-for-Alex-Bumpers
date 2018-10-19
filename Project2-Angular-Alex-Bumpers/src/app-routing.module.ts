import { ProfilePageComponent } from './app/components/profile-page/profile-page.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes, Router } from '@angular/router';

import { RegisterComponent } from 'src/app/components/register/register.component';
import { LoginComponent } from 'src/app/components/login/login.component';
import { NavbarComponent } from 'src/app/components/navbar/navbar.component';
import { MyProfileComponent } from './app/components/my-profile/my-profile.component';
import { UsersComponent } from './app/components/users/users.component';
import { UserLiveSearchComponent } from './app/components/user-live-search/user-live-search.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'edit', component: MyProfileComponent },
  { path: 'profile', component: ProfilePageComponent },
  { path: 'users', component: UsersComponent},
  { path: 'search', component: UserLiveSearchComponent}
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [ RouterModule ],
  declarations: []
})
export class AppRoutingModule { }
