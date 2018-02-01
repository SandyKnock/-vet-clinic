import {NgControl} from "@angular/forms";
/**
 * Created by Владислав on 20.03.2017.
 */


export class MyValidators {
  static regex(pattern: string): Function {
    return (control: NgControl): {[key: string]: any} => {
      return control.value.match(pattern) ? null : {pattern: true};
    };
  }
}
