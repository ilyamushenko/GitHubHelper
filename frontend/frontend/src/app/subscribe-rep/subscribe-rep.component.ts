import { Component, OnInit } from '@angular/core';
import {HttpServiceService} from '../http-service/http-service.service';

class Repository  {
	private name: string;
	private html_url: string;
	private login: string;
	private pushed_at: string;
	
	  public constructor(name: string, html_url: string, login: string, pushed_at: string) {
		this.name = name;
		this.html_url = html_url;
		this.login = login;
		this.pushed_at = pushed_at;
	  }
}

@Component({
  selector: 'app-subscribe-rep',
  templateUrl: './subscribe-rep.component.html',
  styleUrls: ['./subscribe-rep.component.scss']
})
export class SubscribeRepComponent implements OnInit {
	user: any;
	public repositories: Array<Repository>;

  constructor(private httpService: HttpServiceService) { }

  ngOnInit() {
	  this.httpService.get('user').subscribe(
	  data => {
		this.user = data;
		localStorage.setItem('email', data.login);
	});
  }

}
