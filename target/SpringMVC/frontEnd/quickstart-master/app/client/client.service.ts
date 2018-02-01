import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Headers, RequestOptions, Http, Response} from "@angular/http";
import {User} from "../shared_entities/user";
/**
 * Created by Владислав on 02.03.2017.
 */

@Injectable()
export class ClientService {


  constructor(private http: Http,)  {
  }

  getClient(token: string) : Observable<User>{
    var headers = new Headers();
    headers.append("Authorization",token);
   // headers.append("Content-Type", "application/text");
    let options = new RequestOptions({headers: headers});
    return this.http.get('http://localhost:8080/client', options).
    map((resp:Response)=>{
      let user = resp.json();
     // console.log(client);
      return user;
    });
  }

  saveClientService(user: User, token: string){
    console.log("SERVICE");
    var headers = new Headers();
    headers.append("Authorization",token);
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({headers: headers});
    return this.http.post('http://localhost:8080/saveClient',user,options).map((resp:Response)=>{
      return resp;
    });
  }

  elSearch(token: string, search: string) : Observable<any>{
    var headers = new Headers();
    headers.append("Authorization",token);
    headers.append("Content-Type", "application/text");
    let options = new RequestOptions({headers: headers});
    return this.http.post('http://localhost:8080/el_search', search, options).map((resp:Response)=>{
      if(resp.status==200) {
        console.log("OK");
        return resp.json();
      }else {
        console.log("NE OK");
        return null
      }
    })
  }

}
