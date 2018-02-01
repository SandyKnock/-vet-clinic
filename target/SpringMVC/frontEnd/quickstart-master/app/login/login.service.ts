/**
 * Created by Владислав on 09.02.2017.
 */
import {Http, Headers, RequestOptions, Response} from "@angular/http";
import any = jasmine.any;
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable()
export class LoginService{
  constructor(private http: Http) {}

  get(login: string, password: string): Observable<string[]>{
    console.log("Методом пост получаем ****** токен");
    let headers = new Headers();
    let options = new RequestOptions({headers: headers});
    let jsonObjectLonAndPass = {username:login, password:password};
    let jsonObjectSerializesStrLogAndPass = JSON.stringify(jsonObjectLonAndPass);
    return this.http.post('http://localhost:8080/login',jsonObjectSerializesStrLogAndPass,options).map(
      (res:Response)=>{
        let hedersAuthorizationList: string[] = [];
        hedersAuthorizationList.push(res.headers.get("Authorization"));
        hedersAuthorizationList.push(res.headers.get("URL"));
      //  hedersAuthorizationList.push(res.headers.get("Nick"));
        return hedersAuthorizationList;

      }
    );
//  return this.authorization;
}

}
