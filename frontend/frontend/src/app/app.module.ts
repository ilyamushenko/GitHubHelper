import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ToastrModule } from 'ngx-toastr';
import {CommonModule} from '@angular/common';
import {ChartsModule} from 'ng2-charts';
import {AppRoutingModule, routes} from './app.routing';
import { AppComponent } from './app.component';
import {FooterComponent} from './footer/footer.component';
import {AboutUsComponent} from './about-us/about-us.component';
import {AuthorizationComponent} from './authorization/authorization.component';
import {MainPageComponent} from './main-page/main-page.component';
import {UserPageComponent} from './user-page/user-page.component';
import {ToolbarComponent} from './toolbar/toolbar.component';


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    FormsModule,
    ChartsModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    AppRoutingModule,
    NgbModule,
    ToastrModule.forRoot()
  ],
  declarations: [
    AppComponent,
    FooterComponent,
    AboutUsComponent,
    AuthorizationComponent,
    MainPageComponent,
    UserPageComponent,
    ToolbarComponent
  ],
  providers: [
    /*HttpService, {
      provide: HTTP_INTERCEPTORS,
      useClass: MyInterceptor,
      multi: true
    }*/],
  bootstrap: [AppComponent]
})
export class AppModule { }
