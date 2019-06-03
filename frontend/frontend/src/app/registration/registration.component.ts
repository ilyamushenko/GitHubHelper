import { Component, OnInit } from '@angular/core';

  export const development= {
    client_id: 'd55965076aadb13c28e2',
	client_secret: '86f103cd0cb022839ff96e5feaf73b48fd142881',
	redirect_uri: 'http://localhost:4200/main'
  };

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  githubUrl: string = 'https://github.com/login/oauth/authorize?client_id=' + development.client_id + '&scope=user&redirect_uri=' + development.redirect_uri;

  constructor() { }

  ngOnInit() {
  }


}
