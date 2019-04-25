import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {

  private account: string;

  constructor(private router: Router) {
  }

  isAuth(): boolean {
    this.account = localStorage.getItem('email');
    if (this.account === 'Войти') {
      return true;
    } else {
      return false;
    }
  }

  ngOnInit() {
    this.account = localStorage.getItem('username');
  }

  exit(): void {
    localStorage.setItem('email', 'Войти');
    localStorage.setItem('password', '');
    this.router.navigateByUrl('/log');
  }
}
