import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorageService } from '../../services/local-storage.service';
import { log } from 'util';

@Component( {
  selector: 'app-unauthorized',
  templateUrl: './unauthorized.component.html',
  styleUrls: [ './unauthorized.component.scss' ]
} )
export class UnauthorizedComponent implements OnInit {

  public route = '';

  constructor( private router: Router, private storage: LocalStorageService ) {
  }

  ngOnInit() {
  }

  // Backing dashboard =====================================================================================================================
  public backToDashboard(): void {
    this.storage.delete( 'bb_session' );
    this.storage.delete( 'bb_roles' );
    this.router.navigate( [ '', 'account', 'login' ] ).then( r => {
      log( 'Backing...' );
    } );
  }
}
