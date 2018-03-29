import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { CompetitionsService } from '../competitions.service';
import { Competition } from '../competition';

@Component({
  selector: 'app-view-competition',
  templateUrl: './view-competition.component.html',
  styleUrls: ['./view-competition.component.css']
})
export class ViewCompetitionComponent implements OnInit {

  competition: Competition;

  constructor(
  	private route: ActivatedRoute,
  	private competitionsService: CompetitionsService,
  	private location: Location,
  	) { }

  ngOnInit(): void {
  	this.getCompetition();
  }

  getCompetition(): void {
  	const id = +this.route.snapshot.paramMap.get('id');
  	this.competitionsService.getCompetition(id)
  		.subscribe(competition => this.competition = competition);
  }

}
