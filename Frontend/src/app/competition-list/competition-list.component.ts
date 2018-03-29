import { Component, OnInit} from '@angular/core';
import { Competition } from '../competition';
import { CompetitionsService } from '../competitions.service';


@Component({
  selector: 'app-competition-list',
  templateUrl: './competition-list.component.html',
  styleUrls: ['./competition-list.component.css']
})
export class CompetitionListComponent implements OnInit {

  constructor(private competitionsService: CompetitionsService) { }

  competitions: Competition[];

  getCompetitions(): void {
  	this.competitionsService.getCompetitions()
      .subscribe(competitions => this.competitions = competitions)
  }

  ngOnInit() {
  	this.getCompetitions();
  }



}
