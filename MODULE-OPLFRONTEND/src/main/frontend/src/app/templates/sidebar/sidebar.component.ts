import { Component, Input, OnInit } from '@angular/core';
import { SidebarModel } from '../../models/sidebar.model';
import { sidebar } from '../../../environments/sidebar';
import { IS_MOBILE } from '../../include/constants';
import { LocalStorageService } from '../../services/local-storage.service';

declare var UIkit: any;

@Component( {
  selector: 'bdb-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: [ './sidebar.component.scss' ]
} )
export class SidebarComponent implements OnInit {

  @Input( 'enableView' ) public enableView: {};
  public sidebar: SidebarModel;

  public isMobileVersion: boolean;

  constructor( private storage: LocalStorageService ) {

    // Set sidebar configuration
    this.sidebar = sidebar;

    this.isMobileVersion = this.storage.get( IS_MOBILE );
  }

  ngOnInit() {
  }

  // Show View Function ====================================================================================================================
  public showView( view: string ): boolean {
    if ( this.enableView !== undefined ) {
      for ( let i of view.split( '|' ) ) {
        if ( this.enableView[ i ] !== undefined && this.enableView[ i ] ) {
          return this.enableView[ i ];
        }
      }
    }

    return false;
  }

  // Enable submenu ========================================================================================================================
  subEnabled( submenu: { id: string, title: string, routerLink: string[] }[] ): boolean {
    for ( let item of submenu ) {
      if ( this.showView( item.id ) ) {
        return true;
      }
    }

    return false;
  }

  public wck( force: boolean = true ): void {
    if ( this.isMobileVersion && force ) {
      UIkit.offcanvas( '#mobile-sidebar' ).hide();
    }
  }
}