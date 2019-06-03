import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ToastrModule } from 'ngx-toastr';
import {AppRoutingModule, routes} from './app.routing';
import { ComponentsModule } from './components/components.module';
import { AppComponent } from './app.component';
import {CommonModule} from '@angular/common';
import {ChartsModule} from 'ng2-charts';
import {AboutUsComponent} from './about-us/about-us.component';
import { AuthorizationComponent} from './authorization/authorization.component';
import { MainPageComponent} from './main-page/main-page.component';
import { UserPageComponent} from './user-page/user-page.component';
import { AddRepositoryComponent } from './add-repository/add-repository.component';
import { RegistrationComponent } from './registration/registration.component';
import {HttpServiceService} from './http-service/http-service.service';
import {MyInterceptor} from './http-service/http-service.interceptor';
import { SearchPageComponent } from './search-page/search-page.component';
import { SubscribeRepComponent } from './subscribe-rep/subscribe-rep.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    FormsModule,
    ChartsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ComponentsModule,
    RouterModule,
    AppRoutingModule,
    NgbModule,
    ToastrModule.forRoot()
  ],
  declarations: [
    AppComponent,
    AboutUsComponent,
    AuthorizationComponent,
    MainPageComponent,
    UserPageComponent,
    AddRepositoryComponent,
    RegistrationComponent,
    SearchPageComponent,
    SubscribeRepComponent

  ],
  providers: [
    HttpServiceService, {
      provide: HTTP_INTERCEPTORS,
      useClass: MyInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
