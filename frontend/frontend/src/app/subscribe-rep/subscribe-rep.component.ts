import { Component, OnInit } from '@angular/core';
import {HttpServiceService} from '../http-service/http-service.service';

@Component({
  selector: 'app-subscribe-rep',
  templateUrl: './subscribe-rep.component.html',
  styleUrls: ['./subscribe-rep.component.scss']
})
export class SubscribeRepComponent implements OnInit {
	userLogin: string = localStorage.getItem('email');
    userName: string = localStorage.getItem('name');
	commitCount: number;
	count: number;
	commits: Array<any>;
    showCommits = -1;
	fullName: string;
    dates: Array<any>;
	public repositories: Array<any>;

  constructor(private httpService: HttpServiceService) { }

  ngOnInit() {
	  this.httpService.post('list-repo', {
		  login: localStorage.getItem('email'),
		  token: localStorage.getItem('token')
	  }).subscribe(
      data => {
        console.log('repos ' + data.result + ' length ' + data.result.length);
		//this.count = data.result.length;
        this.repositories = data.result;
      },
      error => {});
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

  getCommits(repository: string): void {
    if (this.showCommits > -1) {
      this.showCommits = -1;
    } else {
      this.showCommits = 1;
	  this.fullName = repository;
      this.httpService.get('/repos/' + repository + '/commits').subscribe(
        data => {
          this.commitCount = data.length;
          this.commits = data;
		  this.getCommitsDate(data);
        });
    }
  }

  unsubscribe(repId: string): void {
	  //запрос
	  this.ngOnInit();
  }

}


