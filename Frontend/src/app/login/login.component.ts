import { Component, OnInit } from '@angular/core';
import {SignUpUser} from '../sign-up-user';
import {SignupService} from '../signup.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  {

  model = new SignUpUser('', '', 2, '');
  submitted = false;
  error = false;

  constructor(private loginService: SignupService, private router: Router) { }

  onSubmit() {
    this.submitted = true;
    this.loginService.loginUser(this.model).subscribe(user => {
      if (user === null) {
        this.error = true;
      } else {
        this.error = false;
        localStorage.setItem('user', JSON.stringify(user));
        this.router.navigate(['/competitionList']);
      }
    });

  }

}
