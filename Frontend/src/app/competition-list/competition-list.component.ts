import { Component, OnInit} from '@angular/core';
import { Competition } from '../competition';
import { CompetitionsService } from '../competitions.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-competition-list',
  templateUrl: './competition-list.component.html',
  styleUrls: ['./competition-list.component.css']
})
export class CompetitionListComponent implements OnInit {

  constructor(private competitionsService: CompetitionsService, private router : Router) { }

  competitions: Competition[];

  getCompetitions(): void {
    this.competitionsService.getCompetitions()
      .subscribe(competitions => this.competitions = competitions);
  }

  ngOnInit() {
    if(localStorage.getItem('user') == null){
      this.router.navigateByUrl('/home');
    }
   this.getCompetitions();
  }



}
