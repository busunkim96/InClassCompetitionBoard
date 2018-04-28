import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { CompetitionsService } from '../competitions.service';
import { Competition } from '../competition';
import {User} from '../user';

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
  inputval: number;
  id: number;
  compId: number;
  formData: FormData;

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
    this.formData = new FormData();
  }

  getCompetition(): void {
  	const id = +this.route.snapshot.paramMap.get('id');
  	this.competitionsService.getCompetition(id)
  		.subscribe(competition => {
  		  this.competition = competition;
  		  this.competition.joined = false;
  		});
  }



  getFileContent(): void {
    this.competitionsService.getFileContent(+this.route.snapshot.paramMap.get('id'))
      .subscribe( content => {
        console.log(content);
        this.fileContent =  content;
      });
  }

  joinCompetition(): void {
    this.competitionsService.joinCompetition(this.competition.id, String(this.user.id))
      .subscribe(n => {
        if (n === this.competition.id || n === -1) {
          this.competition.joined = true;
        } else {
          console.log('error joining competition');
        }
      });
    console.log('competition ' + this.competition.id + ' joined!');

  }
  setinputval( event:any ) {
    this.inputval = parseInt(event.target.value, 10);
    this.id = parseInt(event.target.id, 10);
    this.compId = this.competition.id;
  }
  commitChanges(event:any){
    this.competitionsService.updateScore(this.compId,this.id,this.inputval).subscribe(res => {
      console.log(res);
    });
  }

  onFileChange(files) {
    for (const file of files) {
      this.formData.append('fileUpload', file);
    }
  }


  onSubmit() {
    this.competitionsService.uploadSubmission(String(this.competition.id), String(this.user.id), this.formData)
      .subscribe(res => {
        console.log(res);
      });
  }
}
