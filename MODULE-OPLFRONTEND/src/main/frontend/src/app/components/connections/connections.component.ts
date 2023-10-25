import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FtpUserModel } from '../../models/ftp.user.model';
import { ActivatedRoute } from '@angular/router';
import { FtpService } from '../../services/ftp.service';
import { Bbforms } from '../../include/bbforms';
import { Uikit } from '../../include/uikit';
import * as $ from 'jquery';
import { MustMatchValidator } from '../../include/must-match.validator';

@Component({
  selector: 'app-connections',
  templateUrl: './connections.component.html',
  styleUrls: ['./connections.component.scss']
})
export class ConnectionsComponent implements OnInit {

  public userCreationGroup: FormGroup;
  public userConnectionCreationGroup: FormGroup;
  public userCreationSubmitted: boolean;
  public loading: boolean;
  public successMessage: string;
  public errorMessage: string;
  public userList: FtpUserModel[] = [];

  @ViewChild('userCreation', { static: true }) userCreationForm;
  @ViewChild('userConnection', { static: true }) userConnectionCreation;

  constructor(private route: ActivatedRoute, private form: FormBuilder, private users: FtpService) {
    this.successMessage = undefined;
    this.errorMessage = undefined;
  }

  ngOnInit() {
    this.userCreationLogic();
    this.userConnectionCreationLogic();
    this.getUsers();
  }

  /**
   * FTp User form validation.
   */
  userCreationLogic(): void {
    this.userCreationGroup = this.form.group({
      id_user: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required],
      password_confirm: ['', Validators.required]
    }, { validator: MustMatchValidator.mustMatch('password', 'password_confirm') });
  }

  /**
   * FTp Connection form validation
   */
  userConnectionCreationLogic(): void {
    this.userConnectionCreationGroup = this.form.group({
      connection_id: ['', Validators.required],
      connection_type: ['', Validators.required],
      host_name: ['', Validators.required],
      host_ip: ['', Validators.required],
      port: ['', Validators.required],
      description: ['', Validators.required],
      user: ['', Validators.required],
      path: ['', Validators.required],
    }, { validator: Bbforms.validator });
  }

  /**
   * Get all Ftp users
   */
  getUsers(): void {
    this.users.loadUsers().subscribe(response => {
      this.userList = response.result;
    }, error => {
      Uikit.notification().danger(error.error.message !== undefined ? error.error.message : error.message);
    });
  }

  /**
   * Do submit FTP User Creation form
   */
  submitUserCreation(): void {
    this.userCreationSubmitted = true;
    this.loading = true;
    const u: FormGroup = this.userCreationGroup;
    console.log(this.userCreationGroup.value);

    this.users.registerUser({
      userId: u.get('id_user').value,
      username: u.get('username').value,
      password: this.b64EncodeUnicode(u.get('password').value)
    }).subscribe(response => {
      this.loading = false;
      this.successMessage = response.result;
      this.errorMessage = undefined;
      this.userCreationGroup.clearValidators();
      this.userCreationGroup.reset();
      this.clearMessage();
      // Reload users
      this.getUsers();

      $('#main-content').animate({ scrollTop: 0 }, 'slow');
    }, error => {
      console.error(error);
      this.loading = false;
      this.errorMessage = error.error.message !== undefined ? error.error.message : error.message;
      this.successMessage = undefined;

      $('#main-content').animate({ scrollTop: 0 }, 'slow');
    });
  }

  submitFtpConnection(): void {
    this.loading = true;
    const c: FormGroup = this.userConnectionCreationGroup;

    this.users.createFTPConnection({
      connectionId: c.get('connection_id').value,
      hostIp: c.get('host_ip').value,
      hostName: c.get('host_name').value,
      port: c.get('port').value,
      path: c.get('path').value,
      connectionType: c.get('connection_type').value,
      userId: c.get('user').value,
      description: c.get('description').value
    }).subscribe(response => {
      this.loading = false;
      this.successMessage = response.result;
      this.errorMessage = undefined;
      this.userConnectionCreationGroup.clearValidators();
      this.userConnectionCreationGroup.reset();
      this.clearMessage();

      $('#main-content').animate({ scrollTop: 0 }, 'slow');
    }, error => {
      console.error(error);
      this.loading = false;
      this.errorMessage = error.error.message !== undefined ? error.error.message : error.message;
      this.successMessage = undefined;

      $('#main-content').animate({ scrollTop: 0 }, 'slow');
    });
  }

  clearMessage(): void {
    setTimeout(() => {
      this.errorMessage = undefined;
      this.successMessage = undefined;
    }, 2500);
  }

  // Allows the EMI code to be only numerical ========================================================================================
  onlyNumberKey(event) {
    return (event.charCode == 8 || event.charCode == 0) ? null : event.charCode >= 48 && event.charCode <= 57;
  }

  b64EncodeUnicode(str: any) {
    // first we use encodeURIComponent to get percent-encoded UTF-8,
    // then we convert the percent encodings into raw bytes which
    // can be fed into btoa.
    return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g,
      // function toSolidBytes(match, p1) {
      (match, p1) => {
        // console.debug('match: ' + match);
        return String.fromCharCode(("0x" + p1) as any);
      }));
  }
}
