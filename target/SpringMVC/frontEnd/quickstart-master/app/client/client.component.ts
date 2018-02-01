/**
 * Created by Владислав on 02.03.2017.
 */

import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ModalComponent} from "ng2-bs3-modal/components/modal";
import {User} from "../shared_entities/user";
import {ClientService} from "./client.service";
// import {Client} from "./client";

@Component({
  moduleId: module.id,
  selector: 'my-client',
  templateUrl: 'html/client.component.html',
  styleUrls: ['../shared_css/AppCss/app.component.css','../shared_css/app.css', '../shared_css/bootstrap.css','css/admin.css'],
})
export class ClientComponent implements OnInit {
  user: User;
  token: string;
  modal:ModalComponent;
  ssoId: string;
  search = "";
  searchResponse: any;

    constructor(private clientService: ClientService,private activatedRoute: ActivatedRoute) { }

    ngOnInit() {
      this.updateTable();
    }

  private updateTable(){
    this.activatedRoute.params.subscribe(params => {
      this.token = params['token'];
      this.ssoId = params['ssoId'];
      this.clientService.getClient(this.token).subscribe((data)=>{this.user=data;});

    });
  }

  protected openModal(user: User,modal:ModalComponent) {
    this.user = user;
    this.modal = modal;
    modal.animation = true;
    modal.open();
  }

  public save(firstName: string, lastName: string, email: string, password: string) {
    this.user = {
      id:this.user.id,
      firstName:firstName,
      lastName:lastName,
      email:email,
      ssoId:this.user.ssoId,
      password:password,
      userProfiles:[{
        id:this.user.userProfiles[0].id,
        type:this.user.userProfiles[0].type
      }]
    };
    console.log(this.user);
    this.clientService.saveClientService(this.user,this.token).subscribe(data=>{
      console.log(data);
      this.updateTable();
      this.closeModal(this.modal);
    });
  }

  protected closeModal(modal:ModalComponent) {
   modal.close();
  }

  protected elSearch(search: string){
    if(search!=null && search!="" && search!=undefined)
    this.clientService.elSearch(this.token, search).subscribe(data=>{
      if(data!=null) {
        console.log(data);
        this.searchResponse = data;
      }else this.searchResponse = [""];
    });
  }

}
