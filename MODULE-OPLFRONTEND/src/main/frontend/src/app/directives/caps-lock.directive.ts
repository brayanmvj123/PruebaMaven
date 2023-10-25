import { Directive, EventEmitter, HostListener, Output } from '@angular/core';

@Directive( {
  selector: '[bdbCapsLock]'
} )
export class CapsLockDirective {
  @Output( 'bdbCapsLock' ) capsLock = new EventEmitter<Boolean>();

  constructor() {
  }

  @HostListener( 'window:keydown', [ '$event' ] )
  onKeyDown( event: KeyboardEvent ): void {
    this.capsLock.emit( event.getModifierState && event.getModifierState( 'CapsLock' ) );
  }

  @HostListener( 'window:keyup', [ '$event' ] )
  onKeyUp( event: KeyboardEvent ): void {
    this.capsLock.emit( event.getModifierState && event.getModifierState( 'CapsLock' ) );
  }
}
