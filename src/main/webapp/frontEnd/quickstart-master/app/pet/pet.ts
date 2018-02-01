/**
 * Created by Владислав on 02.03.2017.
 */
import {ClientDataPet} from "./ClientDataPet";
import {EmployeeDataPet} from "./EmployeeDataPet";

export class Pet {
  petId: number;
  history: string;
  fullHistory: string;
  numberOfComplaints: number;
  dataLastComplaints: Date;
  nickName: string;
  kindOfAnimal: string;
  client: ClientDataPet;
  employee: EmployeeDataPet;
}
