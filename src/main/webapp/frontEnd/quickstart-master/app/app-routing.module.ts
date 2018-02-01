import {Routes, RouterModule} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {MainPageComponent} from "./mainPage/mainPage.component";
import {NgModule} from "@angular/core";
import { AdminComponent} from "./user/admin.component";
import {ClientComponent} from "./client/client.component";
import {PetComponent} from "./pet/pet.component";
import {EmployeeComponent} from "./employee/employee.component";
import {LocationStrategy, HashLocationStrategy} from "@angular/common";
import {RegistrationComponent} from "./registration/registration.component";
/**
 * Created by Владислав on 06.02.2017.
 */
const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'main',
    component: MainPageComponent
  },
  {
    path: 'AdminMainPage/:token',
    component: AdminComponent
  },
  {
    path: 'ClientMainPage/:token',
    component: ClientComponent
  },
  {
    path: 'pet/:token',
    component: PetComponent
  },
  {
    path: 'EmployeeMainPage/:token',
    component: EmployeeComponent
  },
  {
    path: 'registration',
    component: RegistrationComponent
  },
];
@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes,{ useHash: true })
  ],
  exports: [
    RouterModule
  ],
  providers: [ {provide: LocationStrategy, useClass: HashLocationStrategy}],
  declarations:[]
})
export class AppRoutingModule { }
