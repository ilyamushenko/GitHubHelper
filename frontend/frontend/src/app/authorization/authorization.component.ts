import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpServiceService} from '../http-service/http-service.service';

@Component({
  selector: 'app-authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.css']
})
export class AuthorizationComponent implements OnInit {

  constructor(private route: ActivatedRoute, private httpService: HttpServiceService) { }

  ngOnInit() {
  }

}
