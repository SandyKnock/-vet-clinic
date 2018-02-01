/**
 * Created by Владислав on 06.02.2017.
 */
import {Injectable} from '@angular/core';
import {Http, Response, RequestOptions, Headers} from "@angular/http";
import "rxjs/add/operator/toPromise";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/publish';
import {Observable} from "rxjs";
import {User} from "../shared_entities/user";
import * as http from "selenium-webdriver/http";

@Injectable()
export class AdminService{

  constructor(private http: Http,)  {
  }

  getAllUser(token: string) : Observable<User[]>{
    var headers = new Headers();
    headers.append("Authorization",token);
    let options = new RequestOptions({headers: headers});
    return this.http.get('http://localhost:8080/admin',options).
    map((resp:Response)=>{
      let usersList = resp.json();
      let users : User[] = [];
      for (let index in usersList){
        let user = usersList[index];
        users.push({
          id: user.id,
          ssoId: user.ssoId,
          password: user.password,
          firstName: user.firstName,
          lastName: user.lastName,
          email: user.email,
          userProfiles: user.userProfiles,
        });
     //   console.log(users[index]);
      }
      return users;
    });
  }



  saveUserService(user: User, token: string){
    console.log("SERVICE");
    var headers = new Headers();
    headers.append("Authorization",token);
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({headers: headers});
    return this.http.post('http://localhost:8080/saveUser',user,options).map((resp:Response)=>{
      return resp;
    });
  }

  addElastic(id_el: string, name_el: string,  token: string){
    let objElastic = {id: id_el, name: name_el} ;
    console.info(objElastic);
    var headers = new Headers();
    headers.append("Authorization",token);
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({headers: headers});
    return this.http.post('http://localhost:8080/addElastic',objElastic,options).subscribe();
  }

//TODO добавить контроллеры
  add_user_service(){}
  delete_user_service(){}
}

