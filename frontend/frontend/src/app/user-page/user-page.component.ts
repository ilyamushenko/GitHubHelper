import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

class Repository  {
  private name: string;
  private username: string;
  private commits: string;
  private link: string;

  public constructor(name: string, username: string, commits: string, link: string) {
    this.name = name;
    this.username = username;
    this.commits = commits;
    this.link = link;
  }
}

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {
  private list: Array<Repository>;

  constructor( private router: Router ) { }

  ngOnInit() {
    const r1 = new Repository('GitHubHelper', 'ilyamushenko', '2', 'https://github.com/ilyamushenko/GitHubHelper');
    const r2 = new Repository('Aquarium', 'ilmamen36', '13', 'https://github.com/ilmamen36/Aquarium');
    const r3 = new Repository('PersonTask', 'DeeenDamn', '4', 'https://github.com/DeeenDamn/PersonTask');
    this.list = [ r1, r2, r3 ];
  }

  delete() {

  }

}
