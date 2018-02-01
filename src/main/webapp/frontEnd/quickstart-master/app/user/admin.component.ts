///<reference path="../sort/orderBy.ts"/>
/**
 * Created by Владислав on 06.02.2017.
 */
import {Component, OnInit} from '@angular/core';
import {AdminService} from "./admin.service";
import {User} from "../shared_entities/user";
import {ActivatedRoute} from "@angular/router";
import {ModalComponent} from "ng2-bs3-modal/components/modal";
import {FormGroup} from "@angular/forms";




@Component({
  moduleId: module.id,
  selector: 'my-user',
  templateUrl: 'html/admin.component.html',
  styleUrls: [ '../shared_css/app.css', '../shared_css/bootstrap.css', 'css/admin.css'],
})
export class AdminComponent implements OnInit {
  users: User[]=[];
  user: User;
  token: string;
  modal: ModalComponent;
  id_el: string;
  name_el: string;
  form: FormGroup;

  constructor(private adminService: AdminService,private activatedRoute: ActivatedRoute ) {
  }

   ngOnInit(){
    this.id_el = "";
    this.name_el = "";
    this.user = this.getDefaultModelUser();
    this.updateTable();
  }

  private updateTable(){
    this.activatedRoute.params.subscribe(params => {
      this.token = params['token'];
      this.adminService.getAllUser(this.token).subscribe((data)=>{this.users=data;console.log(this.users)});
    });
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
        id:0,type:""
      }]
    };
  }


  protected openModal(user: User,modal:ModalComponent) {
    this.user = user;
    this.modal = modal;
    modal.animation = true;
    modal.open();
  }



  public delete(id:number){
    //this.userService
  }

  public add(){
  //  this.userService
  }

  public addElastic(id_el: string, name_el: string ){
    console.info(id_el);
    this.id_el = id_el;
    this.name_el = name_el;
    this.adminService.addElastic(id_el, name_el, this.token);

  }

  public save(id: number, firstName: string, lastName: string, email: string, ssoId: string, password: string, profile_id: number, profile_type: string) {
    this.user = {
      id:id,
      firstName:firstName,
      lastName:lastName,
      email:email,
      ssoId:ssoId,
      password:password,
      userProfiles:[{
        id:profile_id,type:profile_type}
        ]
    };
    console.info(this.user);
    this.adminService.saveUserService(this.user,this.token).subscribe(data=>{
      console.log(data);
      this.updateTable();
      this.closeModal(this.modal);
    });
  }

  protected closeModal(modal:ModalComponent) {
    this.user = this.getDefaultModelUser();
    modal.close();
  }

}
