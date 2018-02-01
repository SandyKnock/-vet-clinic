import {Injectable} from "@angular/core";
import {Http, Headers, RequestOptions, Response} from "@angular/http";
import {User} from "../shared_entities/user";
/**
 * Created by Владислав on 17.03.2017.
 */

@Injectable()
export class RegistrationService {


  constructor(private http: Http,) {
  }

  saveUser(user: User) {
    console.log("SERVICE");
    var headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({headers: headers});
    return this.http.post('http://localhost:8080/registration', user, options).map((resp: Response) => {
      return resp;
    });
  }

}
