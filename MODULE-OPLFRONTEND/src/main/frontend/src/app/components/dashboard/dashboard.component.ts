import { Component, ElementRef, HostListener, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { fromEvent, Subscription } from 'rxjs';
import { JwtModel } from '../../models/jwt.model';
import { RolesModel } from '../../models/roles.model';
import { SidebarModel } from '../../models/sidebar.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { environment } from '../../../environments/environment';
import { CookieService } from 'ngx-cookie-service';
import { ActivatedRoute, Router } from '@angular/router';
import { LocalStorageService } from '../../services/local-storage.service';
import { AuthService } from '../../services/auth.service';
import { UserIdleService } from 'angular-user-idle';
import { Jwt } from '../../include/jwt';
import { log } from 'util';
import { map } from 'rxjs/operators';
import { sidebar } from '../../../environments/sidebar';
import { Uikit } from '../../include/uikit';
import { ViewUtil } from '../../include/view.util';
import { Bbforms } from '../../include/bbforms';
import * as $ from 'jquery';
import { DOMAINS_DATA, IS_MOBILE, ROLES_DATA, SESSION_DATA, WIME_DATA } from '../../include/constants';

const eventSource = fromEvent(document, 'mousemove');
const eventPipe = eventSource.pipe(map(event => event.timeStamp.toFixed(0)));

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  host: {
    '(window:resize)': 'onWindowResize($event)'
  }
})
export class DashboardComponent implements OnInit, OnDestroy {

  private upt = 0;
  private modalOpen = false;
  private eventPipe: Subscription;
  public authLoading = false;
  public userData: JwtModel;
  public roles: RolesModel[];
  public sidebar: SidebarModel;
  public enableView: {};
  public loginForm: FormGroup;
  public $event: Event;
  public screenWidth: number = window.innerWidth;
  public isMobileVersion: boolean = this.screenWidth <= environment.mobile_size;
  public sidebarIsOpen = !this.isMobileVersion;
  public database: any;
  public domains: {};
  public base = environment.base;
  public eyeOpened = false;
  public capsLock: boolean;
  public loginEmergente: boolean = false;

  @ViewChild('sidebarContent', { static: false }) private sidebarContent: ElementRef;
  @ViewChild('mainContent', { static: false }) private mainContent: ElementRef;

  constructor(private cookieService: CookieService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private storage: LocalStorageService,
    private auth: AuthService,
    private formBuilder: FormBuilder,
    private userIdle: UserIdleService) {

    // Set sidebar configuration
    this.sidebar = sidebar;

    // Mobile verification
    this.isMobileVersion = this.screenWidth < environment.mobile_size;
    this.storage.save(IS_MOBILE, this.isMobileVersion);
  }

  // Do on component init ================================================================================================================
  ngOnInit() {

    // Domains
    if (this.storage.get(DOMAINS_DATA) !== undefined) {
      this.domains = JSON.parse(atob(this.storage.get(DOMAINS_DATA))) as {};
    }

    // Set user data
    this.userData = Jwt.toObject(this.storage.get(SESSION_DATA));

    // Session validation implementation
    this.sessionVal();
    this.cookieSessionVal();

    // Get user roles
    this.getRoles();

    // Form Login config
    this.formLoginConf();

    // Start watching for user inactivity
    this.userIdle.startWatching();

    // Start watching when user idle is starting.
    this.userIdle.onTimerStart().subscribe(count => {
      if ((environment.expiration_token * 60) - count <= 30) {
        log(`User has been inactive by ${count} seconds, logout in ${(environment.expiration_token * 60) - count} seconds`);
      }
    });

    // Start watch when time is up.
    this.userIdle.onTimeout().subscribe(() => {
      log(`The user has been inactive by ${environment.expiration_token} minutes. Logout the user <>`);

      // Logout popup
      this.logoutPopup();
    });

    // Watching user
    this.eventPipe = eventPipe.subscribe(() => {

      // Reset watching when user active
      this.userIdle.resetTimer();
    });

    // Are you sure to close window?
    $(window).bind('beforeunload', eo => {
      this.storage.save(WIME_DATA, new Date().getTime());

      log('Window refreshed, reloading content...');
    });

    // You was close window previously
    if (this.storage.get(WIME_DATA) !== null) {
      const diff = (new Date().getTime() - Number.parseInt(this.storage.get(WIME_DATA))) / 1000;
      log(`You was close this window ${diff.toFixed(0)} seconds ago.`);

      // Logging out!!!
      if (diff > 15) {
        this.logout();
      }

      log('Valid wime!');
      setTimeout(() => this.storage.delete(WIME_DATA), 1000);
    }
  }

  // Logout Popup ==========================================================================================================================
  private logoutPopup(): void {
    if (this.upt > 0) {
      this.loginEmergente = true;
      Uikit.modal('#logged-out', { bgClose: false, keyboard: false }).show();
    }

    this.modalOpen = true;

    // Logout user
    this.logout(false);

    // Stop watch user activity
    this.userIdle.stopWatching();
  }

  // Toggle dashboard sidebar... =========================================================================================================
  public toggleSidebar(): void {
    if (this.sidebarContent.nativeElement.classList.contains('hidden')) {
      this.sidebarContent.nativeElement.classList.remove('hidden');
      this.mainContent.nativeElement.style.marginLeft = '260px';
      this.sidebarIsOpen = true;
    } else {
      this.sidebarContent.nativeElement.classList.add('hidden');
      this.mainContent.nativeElement.style.marginLeft = '0';
      this.sidebarIsOpen = false;
    }
  }

  // Get logged user roles ===============================================================================================================
  public getRoles(refresh = false): void {
    if (Jwt.isValid(this.storage.get(SESSION_DATA))) {
      if (this.storage.get(ROLES_DATA) !== undefined && this.storage.get(ROLES_DATA) !== '' && !refresh) {
        this.roles = JSON.parse(decodeURIComponent(this.storage.get(ROLES_DATA)));

        // View enable functions
        this.enableView = ViewUtil.enable(this.roles);

        // Event route
        this.dynamicRoute();
      } else {
        this.auth.roles(this.storage.get(SESSION_DATA)).subscribe(response => {
          this.roles = response.result;
          this.storage.save(ROLES_DATA, JSON.stringify(this.roles));

          // View enable functions
          this.enableView = ViewUtil.enable(this.roles);

          // Event route
          this.dynamicRoute();
        }, error => {
          Uikit.notification().danger(error.error.message !== undefined ? error.error.message : error.message);
        });
      }
    }
  }

  // Show View Function ==================================================================================================================
  public showView(view: string): boolean {
    if (this.enableView !== undefined) {
      for (const i of view.split('|')) {
        if (this.enableView[i] !== undefined && this.enableView[i]) {
          return this.enableView[i];
        }
      }
    }

    return false;
  }

  // Check session including user activity. ==============================================================================================
  private sessionVal(): void {

    // Verify if user is active
    if (this.storage.get(SESSION_DATA) !== undefined && Jwt.isValid(this.storage.get(SESSION_DATA))) {

      // Get if current valid session is ending to refresh token
      if (this.upt >= 0) {
        log('renovando usuario activo');

        // Refresh token
        setTimeout(() => this.auth
          .refresh(this.storage.get(SESSION_DATA))
          .subscribe(response => {
            log('Refreshing token...');
            this.storage.save(SESSION_DATA, response.token);

            // Refresh user roles
            //this.getRoles(true);
          }, error => {
            this.logout();
            console.error(error);
          }), 10000);
      }

      // UPT TO ONE
      this.upt = 1;
    }

    // Verify session
    if (this.storage.get(SESSION_DATA) === undefined || !Jwt.isValid(this.storage.get(SESSION_DATA))) {
      if (!this.modalOpen) {
        log('Going to login view...');
        this.router.navigate(['', 'account', 'login']).then(r => {
          // Redirect to login
        });

        // Stop watch user activity
        this.userIdle.stopWatching();
      }
    } else {
      setTimeout(() => {
        this.sessionVal();
      }, (60000 * environment.expiration_token) - (60000 * (environment.expiration_token / 2)));
    }
  }

  // Cookie Session validation ===========================================================================================================
  private cookieSessionVal(): void {
    // Verify session
    if (this.storage.get(SESSION_DATA) === undefined || this.storage.get(SESSION_DATA) === '') {
      this.logoutPopup();
    } else {
      setTimeout(() => {
        this.cookieSessionVal();
      }, 10000);
    }
  }

  // Logout method =======================================================================================================================
  logout(all: boolean = true): void {
    this.storage.delete(SESSION_DATA);
    this.storage.delete(ROLES_DATA);

    if (all) {
      Uikit.modal('#logged-out').hide();
      this.router.navigate(['', 'account', 'login'], {}).then(res => {
        // Nothing to do...
      });
      this.userIdle.stopWatching();
    }
  }

  // Destroy component ===================================================================================================================
  ngOnDestroy(): void {
    this.userIdle.stopWatching();
  }

  // Verify route ========================================================================================================================
  dynamicRoute(): void {
    if (!this.showView(this.activatedRoute.snapshot.firstChild.data.id)) {
      this.router.navigate(['', 'unauthorized'], {
        queryParams: {}
      }).then(res => {
        // Nothing to do...
      });
    }
  }

  // Submit login form ===================================================================================================================
  submitForm(): void {
    if (this.loginForm.invalid) {
      return;
    }

    // Set spinner loading
    this.authLoading = true;

    this.auth
      .doLogin(this.loginForm.get('username').value, this.loginForm.get('password').value, this.loginForm.get('domain').value)
      .subscribe(response => {
        if (Jwt.isValid(response.token)) {

          // Re watch user activity
          this.userIdle.startWatching();

          // Hide login modal
          Uikit.modal('#logged-out').hide();

          // Save session
          this.storage.save(SESSION_DATA, response.token);

          // Verify logged user
          if (Jwt.toObject(response.token).username !== this.userData.username) {
            this.userData = Jwt.toObject(response.token);
            this.router.navigate(['', 'c', 'dashboard']).then(() => null);
          }

          // Get roles
          this.getRoles(true);

          // Set spinner loading
          this.authLoading = false;

          // Inform that modal is closed
          this.modalOpen = false;

          this.loginEmergente = false;

          // Reset values
          this.loginForm.reset();
          this.loginForm.get('domain').setValue('DES1');

        }
      }, error => {
        this.authLoading = false;
        Uikit.notification().danger(error.error.message || error.message);
      });
  }

  // Login form configuration ==============================================================================================================
  formLoginConf(): void {
    // noinspection DuplicatedCode
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      domain: [Object.keys(this.domains).length > 0 ? Object.keys(this.domains)[0] : '', Validators.required]
    }, {
      validator: Bbforms.validator
    });
  }

  // Enable submenu ========================================================================================================================
  subEnabled(submenu: { id: string, title: string, routerLink: string[] }[]): boolean {
    for (const item of submenu) {
      if (this.showView(item.id)) {
        return true;
      }
    }

    return false;
  }

  // On Window Resize ======================================================================================================================
  onWindowResize(event: Event): void {
    this.screenWidth = event.target['innerWidth'];
    this.isMobileVersion = this.screenWidth <= environment.mobile_size;
    this.sidebarIsOpen = !this.isMobileVersion;
    this.storage.save(IS_MOBILE, this.isMobileVersion);

    if (this.isMobileVersion) {
      this.mainContent.nativeElement.removeAttribute('style');
    }
  }

  // Open database =========================================================================================================================
  private initDatabase(): void {
    //
  }

  eye(): void {
    this.eyeOpened = !this.eyeOpened;
  }

  @HostListener('window:click', ['$event'])
  getCapsOnReady(event): void {
    this.capsLock = event.getModifierState && event.getModifierState('CapsLock');
  }

  @HostListener('document:keydown.escape', ['$event'])
  onKeydownHandler(evt: KeyboardEvent) {
    if (this.loginEmergente) {
      Uikit.modal('#logged-out', { bgClose: false, keyboard: false }).show();
    }
  }

}
