import { BrowserModule } from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {AgmCoreModule} from "angular2-google-maps/core";
import {LoginComponent} from "./login/login.component";
import {MainPageComponent} from "./mainPage/mainPage.component";
import {AppComponent} from "./app.component";
import {AppRoutingModule} from "./app-routing.module";

import {AdminService} from "./user/admin.service";
import {LoginService} from "./login/login.service";
import {Ng2Bs3ModalModule} from "ng2-bs3-modal/ng2-bs3-modal";
import {OrderBy} from "./sort/orderBy";
import {AdminComponent} from "./user/admin.component";
import {ClientComponent} from "./client/client.component";
import {PetComponent} from "./pet/pet.component";
import {EmployeeComponent} from "./employee/employee.component";
import {ClientService} from "./client/client.service";
import {PetService} from "./pet/pet.service";
import {RegistrationComponent} from "./registration/registration.component";
import {RegistrationService} from "./registration/registration.service";

@NgModule({//OrderBy need add at provider and declarations
  declarations: [
    AppComponent,
    MainPageComponent,
    LoginComponent,
    AdminComponent,
    ClientComponent,
    PetComponent,
    EmployeeComponent,
    RegistrationComponent,
    OrderBy
  ], //регестрация модуля
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    FormsModule,
    CommonModule,
    AppRoutingModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCS9br0UDztQ2vfhPUwKa9ihvJAb0qHyBI',
    }),
    HttpModule,
    Ng2Bs3ModalModule,

  ],
  providers: [AdminService, ClientService, PetService, LoginService, RegistrationService, OrderBy],
  bootstrap:    [AppComponent ] //сообщаем что для запуска приложения используется AppComponent
})
export class AppModule { }
