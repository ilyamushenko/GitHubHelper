import { Component, OnInit } from '@angular/core';
import {HttpServiceService} from '../http-service/http-service.service';

@Component({
  selector: 'app-subscribe-rep',
  templateUrl: './subscribe-rep.component.html',
  styleUrls: ['./subscribe-rep.component.scss']
})
export class SubscribeRepComponent implements OnInit {
	user: any;
	public repositories: Array<any>;

  constructor(private httpService: HttpServiceService) { }

  ngOnInit() {
    this.httpService.post('user/check', {
      chatId: localStorage.getItem('chatId'),
      token: localStorage.getItem('token')
    }).subscribe(
      data => {
        console.log('data ' + data);
        this.user = data;
        localStorage.setItem('email', data.login);
      },
      error => {});

    this.httpService.get('user/myrepositories').subscribe(
      data => {
        console.log(data);
        this.repositories = data;
      });
  }

}


