import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';


import { AppComponent } from './app.component';
import { CompetitionListComponent } from './competition-list/competition-list.component';
import { CompetitionsService } from './competitions.service';
import { UserService } from './user.service';
import { AppRoutingModule } from './/app-routing.module';
import { CreateCompetitionComponent } from './create-competition/create-competition.component';
import { ViewCompetitionComponent } from './view-competition/view-competition.component';
import { UsersComponent } from './users/users.component';

@NgModule({
  declarations: [
    AppComponent,
    CompetitionListComponent,
    CreateCompetitionComponent,
    ViewCompetitionComponent,
    UsersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [CompetitionsService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule { 
	title = 'In Class Competition Board';
}
