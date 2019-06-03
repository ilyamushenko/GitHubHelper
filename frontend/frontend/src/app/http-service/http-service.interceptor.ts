import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class MyInterceptor implements HttpInterceptor {
	
	intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (localStorage.getItem('token') === null || (req.url === '/access_token' && req.method === 'POST')) {
      return next.handle(req);
    } else {
      const authReq = req.clone({
        headers: req.headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'))
      });
      return next.handle(authReq);
    }
  }
}
