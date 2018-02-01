/**
 * Created by Владислав on 17.03.2017.
 */

import { Component, OnInit } from '@angular/core';
import {User} from "../shared_entities/user";
import {RegistrationService} from "./registration.service";
import {ActivatedRoute} from "@angular/router";
import {FormGroup, FormBuilder, Validators, FormControl} from '@angular/forms';

@Component({
  moduleId: module.id,
  selector: 'my-reg',
  templateUrl: 'html/registration.component.html',
  styleUrls: [ '../shared_css/app.css', '../shared_css/bootstrap.css','css/reg.css'],
  // styleUrls: ['css/login.component.css'],
})
export class RegistrationComponent implements OnInit {
  users: User[]=[];
  user: User;
  form: FormGroup;

  constructor(private registrationService: RegistrationService,private activatedRoute: ActivatedRoute, private _fb: FormBuilder) { }

  ngOnInit() {
    this.user = this.getDefaultModelUser();
  }

  private getDefaultModelUser(){
    return this.user = {
      id:0,
      firstName:"",
      lastName:"",
      email:"",
      ssoId:"",
      password:"",
      userProfiles:[{
        id:1,type:"USER"
      }]
    };
  }

  private registration(firstName: string, lastName: string, email: string, ssoId: string, password: string){
    this.user = {
      id:this.user.id,
      firstName:firstName,
      lastName:lastName,
      email:email,
      ssoId:ssoId,
      password:password,
      userProfiles:[{
        id:this.user.userProfiles[0].id,
        type:this.user.userProfiles[0].type
      }]
    };
    console.log(this.user);

    this.registrationService.saveUser(this.user)
      .subscribe(data=>{
      console.log(data);
     // this.updateTable();
    //  this.closeModal(this.modal);
    });
  }

}
