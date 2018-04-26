import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { AppComponent } from './app.component';
import { CompetitionListComponent } from './competition-list/competition-list.component';
import { CompetitionsService } from './competitions.service';
import { UserService } from './user.service';
import { AppRoutingModule } from './/app-routing.module';
import { CreateCompetitionComponent } from './create-competition/create-competition.component';
import { ViewCompetitionComponent } from './view-competition/view-competition.component';
import { UsersComponent } from './users/users.component';
import { RegisterComponent } from './register/register.component';
import {SignupService} from './signup.service';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    CompetitionListComponent,
    CreateCompetitionComponent,
    ViewCompetitionComponent,
    UsersComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [CompetitionsService, UserService, SignupService],
  bootstrap: [AppComponent]
})
export class AppModule {
	title = 'In Class Competition Board';
}
