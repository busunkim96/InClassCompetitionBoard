import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CompetitionListComponent } from './competition-list/competition-list.component';
import { CreateCompetitionComponent } from './create-competition/create-competition.component';
import { ViewCompetitionComponent } from './view-competition/view-competition.component';
import { UsersComponent } from './users/users.component';
import {RegisterComponent} from './register/register.component';

const routes: Routes = [
{ path: 'competitionList', component: CompetitionListComponent },
{ path: '', redirectTo: '/competitionList', pathMatch: 'full'},
{ path: 'createCompetition', component: CreateCompetitionComponent},
{ path: 'viewCompetition/:id', component: ViewCompetitionComponent},
{ path: 'viewUsers', component: UsersComponent},
{ path: 'register', component: RegisterComponent}

];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports : [ RouterModule ]
})
export class AppRoutingModule { }
