import { Component, OnInit } from '@angular/core';
import {Competition} from '../competition';
import {CompetitionsService} from '../competitions.service';
import {CompetitionResponse} from '../competition-response';
import {FormGroup} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create-competition',
  templateUrl: './create-competition.component.html',
  styleUrls: ['./create-competition.component.css']
})
export class CreateCompetitionComponent implements OnInit {
  userName: string;
  userId: number;
  model: Competition;
  success: string;
  formData: FormData;


  constructor(private competitionService: CompetitionsService , private router: Router) {
    this.model = new Competition();
  }

  ngOnInit() {
    this.success = null;
    console.log(localStorage);
    let user = localStorage.getItem('user');
    if (user == null) {
      this.router.navigateByUrl('/home');
    }
    user = JSON.parse(user);
    this.userId = user['id'];
    this.userName = user['userName'];
    this.formData = new FormData();
  }
  onFileChange(files) {
    for (const file of files) {
      this.formData.append('fileUpload', file);
    }
  }

  onSubmit() {
    const competition = {};
    competition['userName'] = this.userName;
    competition['competitionName'] = this.model.competitionName;
    competition['endDate'] = this.model.endDate;
    competition['className'] = this.model.className;
    competition['description'] = this.model.description;
    console.log(this.model.testcase);
    this.competitionService.createCompetition(competition).subscribe((response) => {
      if (response != null) {
        this.formData.append( 'compId', response['status']);
        this.competitionService.uploadTestcase
        (this.formData).subscribe(res => {
          console.log(res);
          this.success = response['message'];
          this.model = new Competition();
        });
      }
    });
  }

}
