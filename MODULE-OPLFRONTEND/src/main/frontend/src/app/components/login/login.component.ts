import { Component, HostListener, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { LocalStorageService } from '../../services/local-storage.service';
import { environment } from '../../../environments/environment';
import { fromEvent } from 'rxjs';
import { map } from 'rxjs/operators';
import { Jwt } from '../../include/jwt';
import { Bbforms } from '../../include/bbforms';
import { Uikit } from '../../include/uikit';
import { log } from 'util';
import { MatDialog } from '@angular/material';
import { ModalAuthprocessComponent } from 'src/app/templates/modal-authprocess/modal-authprocess.component';

const eventSource = fromEvent( document, 'mousemove' );
const eventPipe = eventSource.pipe( map( event => event.timeStamp.toFixed( 0 ) ) );

@Component( {
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: [ './login.component.scss' ],
  host: {
    '(window:click)': 'getCapsOnReady($event)'
  }
} )
export class LoginComponent implements OnInit {

  public loading: boolean;
  public loginForm: FormGroup;
  public loginError = false;
  public errorMessage: string;
  public capsLock: boolean;
  public eyeOpened = false;
  public domainList: {} = {};
  public authType: number = 0;
  $event: boolean;
  public unAuth: boolean = false;

  @ViewChild( 'loginElement', { static: true } ) loginFormElement;

  constructor( private loginService: AuthService,
               private formBuilder: FormBuilder,
               private cookieService: CookieService,
               private router: Router,
               private storage: LocalStorageService,
               private dialog: MatDialog, ) {
  }

  ngOnInit() {
    eventPipe.subscribe( val => {
    } ).unsubscribe();

    // Verify session
    if ( this.storage.get( 'bb_session' ) !== undefined ) {
      if ( Jwt.isValid( this.storage.get( 'bb_session' ) ) ) {
        this.router.navigate( [ '' ], {} ).then( r => {
          // Redirect to dashboard
        } );
      }
    }

    // noinspection DuplicatedCode
    this.loginForm = this.formBuilder.group( {
      username: [ '', Validators.required ],
      password: [ '', Validators.required ],
      domain: [ '', Validators.required ]
    }, {
      validator: Bbforms.validator
    } );

    if ( this.storage.get( 'wime' ) !== null ) {
      this.storage.delete( 'wime' );
    }

    // Get domains
    this.getDomains();

    // Get authentication type
    this.getType();
  }

  showUnAuth(): void {
    this.dialog.open( ModalAuthprocessComponent, {
      width: '900px',
    } );
  }

  submitForm(): void {
    if ( this.loginForm.invalid ) {
      return;
    }

    // Set spinner loading
    this.loading = true;
    this.loginService
      .doLogin( this.loginForm.get( 'username' ).value, this.loginForm.get( 'password' ).value, this.loginForm.get( 'domain' ).value )
      .subscribe( response => {
        if ( Jwt.isValid( response.token ) ) {
          // Auth
          this.unAuth = false;
          // Save session
          this.storage.save( 'bb_session', response.token );

          // Redirect to dashboard panel
          this.router.navigate( [ '' ], {} ).then( r => {
            this.loading = false;
          } );
        } else {
          this.loginError = true;
          this.loading = false;
          this.errorMessage = 'Token inválido, hay un problema con el autenticador...';
          console.error( 'Invalid token...' );
        }
      }, error => {
        this.loginError = true;
        this.loading = false;
        this.errorMessage = error.error.message || 'Hubo un error inesperado al intentar autenticar el usuario.';
        this.unAuth = error.status === 401;
        console.error( error );
      } );
  }

  /**
   * Show form custom error
   * @param key form input field
   * @param validation form input validation
   */
  showError( key: string, validation: string ): boolean {
    const field = this.loginForm.get( key );
    return field.touched && field.invalid;
  }

  eye(): void {
    this.eyeOpened = !this.eyeOpened;
  }

  whenClose(): void {
    setTimeout( () => {
      this.loginError = false;
    }, 1000 );
  }

  getDomains(): void {
    this.loginService.getDomains().subscribe( response => {
      this.domainList = response.result;

      // Save session
      this.storage.save( 'bb_domains', btoa( JSON.stringify( this.domainList ) ) );

      this.loginForm.setValue( {
        username: '',
        password: '',
        domain: Object.keys( this.domainList ).length > 0 ? Object.keys( this.domainList )[ 0 ] : ''
      } );
    }, error => {
      Uikit.notification().danger( error.error.message || error.message );
    } );
  }

  getType(): void {
    this.loginService.getAuthType().subscribe( response => {
      this.authType = response.status.code;
    }, error => {
      Uikit.notification().danger( error.error.message || error.message );
    } );
  }

  @HostListener( 'window:click', [ '$event' ] )
  getCapsOnReady( event ): void {
    this.capsLock = event.getModifierState && event.getModifierState( 'CapsLock' );
  }
}
