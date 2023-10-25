import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-modal-authprocess',
  templateUrl: './modal-authprocess.component.html',
  styleUrls: ['./modal-authprocess.component.scss']
})
export class ModalAuthprocessComponent implements OnInit {
  
  public showAccordeon: boolean = false;

  constructor() {
  }
  ngOnInit(): void {
    setTimeout( () => this.showAccordeon = true, 150 );
  }

}
