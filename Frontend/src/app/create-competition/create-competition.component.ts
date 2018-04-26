import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-competition',
  templateUrl: './create-competition.component.html',
  styleUrls: ['./create-competition.component.css']
})
export class CreateCompetitionComponent implements OnInit {
  userName: string;
  userId: number;

  constructor() { }

  ngOnInit() {
    console.log(localStorage)
    const temp = Object.keys(localStorage);
    let user = localStorage.getItem(temp[0]);
    user = JSON.parse(user);
    this.userId = user['id'];
    this.userName = user['userName'];
  }

}
