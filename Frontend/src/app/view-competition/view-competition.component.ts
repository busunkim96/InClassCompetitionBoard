import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { CompetitionsService } from '../competitions.service';
import { Competition } from '../competition';
import {User} from '../user';
import {C} from '@angular/core/src/render3';

@Component({
  selector: 'app-view-competition',
  templateUrl: './view-competition.component.html',
  styleUrls: ['./view-competition.component.css']
})
export class ViewCompetitionComponent implements OnInit {

  competition: Competition;
  user: User;
  isFaculty: boolean;
  fileContent: string;

  constructor(
    private route: ActivatedRoute,
    private competitionsService: CompetitionsService,
    private location: Location
    ) {
    this.competition = new Competition();
  }

  ngOnInit(): void {
    if (localStorage.getItem('user') != null) {
      this.user = JSON.parse(localStorage.getItem('user'));
      if ( this.user['type'] === 2) {
        this.isFaculty = true;
      } else if (this.user['type'] === 1) {
        this.isFaculty = false;
      }
    }
    this.getCompetition();
    this.getFileContent();
  }

  getCompetition(): void {
  	const id = +this.route.snapshot.paramMap.get('id');
  	this.competitionsService.getCompetition(id)
  		.subscribe(competition => this.competition = competition);
  }

  getFileContent(): void {
    this.competitionsService.getFileContent(+this.route.snapshot.paramMap.get('id'))
      .subscribe( content => {
        console.log(content);
        this.fileContent =  content;
      });
  }

  joinCompetition(): void {
    /*todo: send request to backend to join competition */
    console.log('competition ' + this.competition.id + ' joined!');
    this.competition.joined = true;

  }

}
