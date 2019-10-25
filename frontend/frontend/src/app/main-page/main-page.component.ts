import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {ActivatedRoute} from '@angular/router';
import {HttpServiceService} from '../http-service/http-service.service';

export const development = {
    client_id: 'd55965076aadb13c28e2',
	client_secret: '86f103cd0cb022839ff96e5feaf73b48fd142881',
	redirect_uri: 'http://localhost:4200/'
  };

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
	githubUrl: string = 'https://github.com/login/oauth/authorize?client_id=' + development.client_id + '&scope=user&redirect_uri=' + development.redirect_uri;
	id: string = null;
	login: string = localStorage.getItem('email');
	success: boolean = false;
	

  constructor(private route: ActivatedRoute, private router: Router, private httpService: HttpServiceService) {
	  if (localStorage.getItem('email') === 'Войти'){
	this.ngOnInit();
	  }
	  //this.isUserAuth(localStorage.getItem('email'));
  }

  ngOnInit() { 
  console.log(localStorage.getItem('email'));
	  console.log(this.login);
	  if (localStorage.getItem('email') === 'Войти') {
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
				 this.httpService.get('user').subscribe(
	  data => {
		localStorage.setItem('email', data.login);
		console.log(data.login);
	});
			 }		 
		 });
	}
  }
  
  func(): boolean {
	  if (localStorage.getItem('email') === 'Войти') {
		  return true;
	  } else {
		  return false;
	  }
  }
  
  isUserAuth(login: string): boolean {
	  //console.log('im here');
	  this.httpService.post('/auth/authenticate', {
		  login: login,
      token: localStorage.getItem('token')
	  }).subscribe(
		  data => {
			  console.log(data);
		  });
	  if (this.success) {
      this.router.navigateByUrl('info');
      return true;
    }
	  else {
	    return false;
    }
  }
  
  getChatId(chatId: string): void {
	  this.id = chatId;
	  localStorage.setItem('chatId', chatId);
    this.httpService.post('/auth/registration', {
      login: localStorage.getItem('email'),
      idChat: chatId,
      token: localStorage.getItem('token')
    }).subscribe(
      data => {
        console.log(data);
      });
    this.router.navigateByUrl('info');
  }

}
