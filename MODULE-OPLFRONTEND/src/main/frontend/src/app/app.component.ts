import { Component } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { filter, map } from 'rxjs/operators';

@Component( {
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: [ './app.component.scss' ]
} )
export class AppComponent {
  title = 'Banco de Bogotá';

  /**
   * App Component.
   * @param router SPA router
   * @param activatedRoute current route
   * @param titleService title service
   */

  constructor( private router: Router, private activatedRoute: ActivatedRoute, private titleService: Title ) {
  }

  /**
   * Change browser title dynamically.
   * @author Jose Buelvas <jbuelva@bancodebogota.com.co>
   */
  private dynamicSiteTitle() {
    this.router.events.pipe(
      filter( event => event instanceof NavigationEnd ),
      map( () => {
        const child = this.activatedRoute.firstChild;
        if ( child.snapshot.data.title ) {
          return child.snapshot.data.title;
        }

        return this.titleService.getTitle();
      } )
    ).subscribe( ( ttl: string ) => {
      this.titleService.setTitle( ttl );
    } );
  }

  ngOnInit(): void {
    this.dynamicSiteTitle();
  }
}
