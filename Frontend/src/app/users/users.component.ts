import { Component, OnInit, Inject } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})

export class UsersComponent implements OnInit {


//  users = [
//     { name: 'Amy Waters',  selected: true, id: 12 },
//      { name: 'Charles Sannes',  selected: false, id: 2},
//    ]

  constructor(private userService: UserService) { }

  unapprovedUsers: User[];
  approvedUsers: User[];

  ngOnInit() {
  	this.getUsersToBeApproved();
  	this.getApprovedUsers();
  }

  approveUsers() {
    const selected = this.unapprovedUsers.filter(u => u.selected);
    console.log(selected);
    const selectedIds = selected.map(u => u.userid);
    console.log(selectedIds);
    this.userService.acceptUsers(selectedIds);


  }

  getUsersToBeApproved(): void {
  	this.userService.getUsers()
  		.subscribe(u => this.unapprovedUsers = u.filter(u => !u.selected));

  }

  getApprovedUsers(): void {

  	this.userService.getUsers()
  		.subscribe(u => this.approvedUsers = u.filter(u => u.selected));
  }





}
