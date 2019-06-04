import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpServiceService} from '../http-service/http-service.service';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.scss']
})
export class SearchPageComponent implements OnInit {
	selectedPage = 0;
  repos: Array<any>;
  dates: Array<any>;
  count: number;
  resCount: number;
  commits: Array<any>;
  showCommits = -1;
  pages = 0;

  constructor(private route: ActivatedRoute, private httpService: HttpServiceService) { }

  ngOnInit() {
	this.selectedPage = Number(localStorage.getItem('page'));
	this.getInfo();
  }

  goToPage(index: number, key: string) {
    localStorage.setItem(key, (index).toString());
    this.ngOnInit();
  }

  changePage(page: number) {
    if (localStorage.getItem('page') === '1' && page === -1) {
      return;
    } else {
      localStorage.setItem('page', (Number(localStorage.getItem('page')) + page).toString());
    }
    this.ngOnInit();
  }

  createRange(): number[] {
    let array: number[] = [];
	if (this.pages <= 5) {
		for (let i = 1; i <= this.pages; i++) {
		  array.push(i);
		}
	} else {
		array = [Number(this.selectedPage), Number(this.selectedPage + 1), Number(this.selectedPage + 2)];
	}
    return array;
  }

  search(findString: string): void {
    localStorage.setItem('searchString', findString);
    this.ngOnInit();
  }

  getDate(repos: Array<any>): void {
	  this.dates = Array<any>(this.count);
	  let i: number;
	  for (i = 0; i < this.count; i++) {
		  this.dates[i] = repos[i].pushed_at;
		  console.log(repos[i].pushed_at);
		  let res = repos[i].pushed_at.split('T', 2);
		  let data = res[0].split('-', 3);
		  let time = res[1].split(':', 2);
		  this.repos[i].pushed_at = data[2].toString() + '.' + data[1].toString() + '.' + data[0].toString() + ' ' + time[0].toString() + ':' + time[1].toString();
		}
  }

  getCommitsDate(commits: Array<any>): void {
	  this.dates = Array<any>(this.count);
	  let i: number;
	  for (i = 0; i < this.count; i++) {
		  this.dates[i] = commits[i].commit.author.date;
		  let res = commits[i].commit.author.date.split('T', 2);
		  let data = res[0].split('-', 3);
		  let time = res[1].split(':', 2);
		  this.commits[i].commit.author.date = data[2].toString() + '.' + data[1].toString() + '.' + data[0].toString() + ' ' + time[0].toString() + ':' + time[1].toString();
		}
  }

  getCommits(id: string, owner: string, repository: string): void {
	  if (this.showCommits > -1) {
		  this.showCommits = -1;
	  } else {
		  this.showCommits = Number(id);
		  this.httpService.get('/repos/' + owner + '/' + repository + '/commits').subscribe(
		  data => {
			  this.count = data.length;
			  console.log(data.length);
			  this.commits = data;
			  this.getCommitsDate(data);
		  });
	  }
  }

  getInfo(): void {
    this.httpService.get('/search/repositories?q=' + localStorage.getItem('searchString') + '&page=' + localStorage.getItem('page')).subscribe(
	  data => {
		this.repos = data.items;
		this.count = Number(data.total_count);
		this.resCount = Number(data.total_count);
		this.pages = Number(Number(this.resCount / 30).toFixed());
		this.getDate(data.items);
	});
  }

  subscribe(repository): void {
    this.httpService.post('user/subscribe', {
      repositoryFullName: repository
    }).subscribe(
      data => {
        console.log(data);
      }
    );
  }
}


