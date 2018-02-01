/**
 * Created by Владислав on 06.02.2017.
 */
import {Component} from '@angular/core';
import {LoginService} from "./login.service";
import {ActivatedRoute, Router} from "@angular/router";
import {DomSanitizer} from "@angular/platform-browser";


@Component({
  moduleId: module.id,
  selector: 'my-login',
  templateUrl: 'html/login.component.html',
  styleUrls: ['css/login.component.css'],
})
export class LoginComponent{
  login: string;
  password: string;
  token: string;
  URL: string;

  constructor(private loginService: LoginService,private router: Router,private sanitizer: DomSanitizer){}

  getParam(){
    this.loginService.get(this.login,this.password).subscribe(
      (res)=>{
        this.token = res[0];
        this.URL = res[1];
        //this.router.navigate([this.sanitizer.(this.URL),this.token]);
        this.router.navigate([this.URL,this.token]);
      //  window.location.href = this.URL;
      });
  }
}
