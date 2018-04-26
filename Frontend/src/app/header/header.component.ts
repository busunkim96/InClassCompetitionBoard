import { Component, OnInit } from '@angular/core';
import {User} from '../user';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isFaculty: boolean;
  isLoggedIn: boolean;
  user: {};
  constructor(private router: Router) { }

  ngOnInit() {
    if (localStorage.getItem('user') != null){
      this.user = JSON.parse(localStorage.getItem('user'));
      if (this.user != null) {
        this.isLoggedIn = true;
      }
      if ( this.user['type'] === 2) {
        this.isFaculty = true;
      } else if (this.user['type'] === 1) {
        this.isFaculty = false;
      }
    }
  }
  onSignout() {
    localStorage.clear();
    this.router.navigateByUrl('');
    this.isLoggedIn = false;
    this.isFaculty = false;
  }

}
