import {UserProfiles} from "./userProfiles";
/**
 * Created by Владислав on 07.02.2017.
 */

export class User{
  id: number;
  ssoId: string;
  password: string;
  firstName: string;
  lastName: string;
  email: string;
  userProfiles: UserProfiles[];
}
