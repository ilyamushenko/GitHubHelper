import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {
  myHeaders = new HttpHeaders();
	
  constructor(private http: HttpClient) {
	this.myHeaders.append('Content-type', 'Application/json');
  }
  
  get(url: string): Observable<any> {
    return this.http.get(url, {headers: this.myHeaders});
  }
  
  post(url: string, body: any): Observable<any> {
	 return this.http.post(url, body, {headers: this.myHeaders});
  }
}
