import {Injectable} from "@angular/core";
import {Http, Headers, RequestOptions, Response} from "@angular/http";
import {Observable} from "rxjs";
import {Pet} from "./pet";
import {Image} from "./Image";
/**
 * Created by Владислав on 02.03.2017.
 */

@Injectable()
export class PetService{

  constructor(private http: Http) {}

  getAllPets(token: string) : Observable<Pet[]>{
    var headers = new Headers();
    headers.append("Authorization",token);
    let options = new RequestOptions({headers: headers});
    return this.http.get('http://localhost:8080/getAllPets',options).
    map((resp:Response)=>{
      let petList = resp.json();
      let pets : Pet[] = [];
      for (let index in petList){
        let pet = petList[index];
        console.log(pet.client)
        pets.push({
          petId: pet.petId,
          history: pet.history,
          fullHistory: pet.fullHistory,
          numberOfComplaints: pet.numberOfComplaints,
          dataLastComplaints: pet.dataLastComplaints,
          nickName: pet.nickName,
          kindOfAnimal: pet.kindOfAnimal,
          client: pet.client,
          employee: pet.employee,
        });
      }
      return pets;
    });
  }

  getAllImage(token: string,imagesID: ImageId[]): Observable<any> {
    var headers = new Headers();
    headers.append("Authorization",token);
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({headers: headers});
    return this.http.post('http://localhost:8080/postAllImages',imagesID,options).map((resp:Response)=>{
      return resp.json();
    });

  }

}
