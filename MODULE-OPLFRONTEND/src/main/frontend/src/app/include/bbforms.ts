/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBF-FRONT was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
import { FormGroup } from '@angular/forms';

export class Bbforms {

  // Content Type formats
  public static X_WWW_FORM_URL_ENCODED = { 'Content-Type': 'application/x-www-form-urlencoded' };
  public static APPLICATION_JSON = { 'Content-Type': 'application/json' };

  /**
   * Form group validations
   * @param controlName form control name (<input>)
   * @param matchingControlName if control name is true
   */
  public static validator( controlName: string, matchingControlName: string ): any {
    return ( formGroup: FormGroup ) => {
      const control = formGroup.controls[ controlName ];
      const matchingControl = formGroup.controls[ matchingControlName ];

      if ( matchingControl.errors && !matchingControl.errors.mustMatch ) {
        // return if another validator has already found an error on the matchingControl
        return;
      }

      // set error on matchingControl if validation fails
      if ( control.value !== matchingControl.value ) {
        matchingControl.setErrors( { mustMatch: true } );
      } else {
        matchingControl.setErrors( null );
      }
    };
  }
}
