import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { CompetitionListComponent } from './competition-list/competition-list.component';
import { CompetitionsService } from './competitions.service';
import { AppRoutingModule } from './/app-routing.module';
import { CreateCompetitionComponent } from './create-competition/create-competition.component';
import { ViewCompetitionComponent } from './view-competition/view-competition.component';

@NgModule({
  declarations: [
    AppComponent,
    CompetitionListComponent,
    CreateCompetitionComponent,
    ViewCompetitionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [CompetitionsService],
  bootstrap: [AppComponent]
})
export class AppModule { 
	title = 'In Class Competition Board';
}
