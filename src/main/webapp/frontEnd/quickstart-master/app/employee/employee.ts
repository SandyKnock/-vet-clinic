import {UserProfiles} from "../shared_entities/userProfiles";
/**
 * Created by Владислав on 02.03.2017.
 */
export class Employee{
  id: number;
  ssoId: string;
  password: string;
  firstName: string;
  lastName: string;
  email: string;
  userProfiles: UserProfiles[];
}
