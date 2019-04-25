import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-repository',
  templateUrl: './add-repository.component.html',
  styleUrls: ['./add-repository.component.scss']
})
export class AddRepositoryComponent implements OnInit {
  private error = false;

  constructor(private router: Router) { }

  ngOnInit() {
  }

  create(link: string): void {
    this.error = false;
    // обработка ссылки
    this.router.navigateByUrl('/user');
  }

}
