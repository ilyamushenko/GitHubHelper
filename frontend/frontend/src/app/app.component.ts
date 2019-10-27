import {Component, OnInit} from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  ngOnInit(): void {
	  localStorage.setItem('page', '1');
	  localStorage.setItem('isAuth', 'false');
    if (localStorage.getItem('email') === null) {
      localStorage.setItem('email', 'Войти');
    }
  }
}