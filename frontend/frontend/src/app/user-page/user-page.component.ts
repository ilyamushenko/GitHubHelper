import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpServiceService} from '../http-service/http-service.service';
import { development } from '../main-page/main-page.component';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {
  user: any;
  repos: Array<any>;
  dates: Array<any>;
  count: number;
  commits: Array<any>;
  showCommits: number = -1;

  constructor(private route: ActivatedRoute, private httpService: HttpServiceService) { }

  ngOnInit() {
	if (localStorage.getItem('token') === null) {
		var code: string;
		this.route.queryParams.subscribe(
		data => {
		 code = data['code'];
		});
		this.httpService.post('/login/oauth/access_token?' +
		 'client_id=' + development.client_id +
		 '&client_secret=' + development.client_secret +
		 '&code=' + code, null).subscribe(
		 token => {},
		 error => {
			 if (error.status === 200) {
				 localStorage.setItem('token', error.error.text.split('&')[0].split('=')[1]);
				 this.getInfo();
			 }		 
		 });
	} else {
		this.getInfo();
	}
  }
  
  getDate(repos: Array<any>): void {
	  this.dates = Array<any>(this.count);
	  var i: number;
	  for (i = 0; i < this.count; i++) {
		  this.dates[i] = repos[i].pushed_at;
		  var res = repos[i].pushed_at.split('T', 2);
		  var data = res[0].split('-', 3);
		  var time = res[1].split(':', 2);
		  this.repos[i].pushed_at = data[2].toString() + '.' + data[1].toString() + '.' + data[0].toString() + ' ' + time[0].toString() + ':' + time[1].toString(); 
		}
  }
  
  getCommitsDate(commits: Array<any>): void {
	  this.dates = Array<any>(this.count);
	  var i: number;
	  for (i = 0; i < this.count; i++) {
		  this.dates[i] = commits[i].commit.author.date;
		  var res = commits[i].commit.author.date.split('T', 2);
		  var data = res[0].split('-', 3);
		  var time = res[1].split(':', 2);
		  this.commits[i].commit.author.date = data[2].toString() + '.' + data[1].toString() + '.' + data[0].toString() + ' ' + time[0].toString() + ':' + time[1].toString(); 
		}
  }
  
  getCommits(id:string, owner: string, repository: string): void {
	  if (this.showCommits > -1) {
		  this.showCommits = -1;
	  } else {
		  this.showCommits = Number(id);
		  this.httpService.get('/repos/' + owner + '/' + repository + '/commits').subscribe(
		  data => {
			  console.log(data);
			  this.count = data.length;
			  this.commits = data;
			  this.getCommitsDate(data);
		  });
	  }
  }
  
  getInfo(): void {	  
	this.httpService.post('user/check', {
		chatId: localStorage.getItem('chatId'),
		token: localStorage.getItem('token')
	}).subscribe(
	data => {
		console.log('data ' + data);
	},
	error => {
		console.log('error ' + error);
		this.user = error;
		localStorage.setItem('email', error.login);
	});
    /*this.httpService.get('user').subscribe(
	  data => {
		this.user = data;
		localStorage.setItem('email', data.login);
	});*/
  
	this.httpService.get('user/repos').subscribe(
	  data => {
		this.repos = data;
		this.count = Number(data.length);
		this.getDate(data);
	});
  }

  delete() {

  }

}
