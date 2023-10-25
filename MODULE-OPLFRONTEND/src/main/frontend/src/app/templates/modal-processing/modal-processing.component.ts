import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-modal-processing',
  templateUrl: './modal-processing.component.html',
  styleUrls: ['./modal-processing.component.scss']
})
export class ModalProcessingComponent implements OnInit {

  constructor( private dialogRef: MatDialogRef<ModalProcessingComponent>, @Inject( MAT_DIALOG_DATA ) public data: {
    title?: string,
    description?: string,
    icon?: string,
    loadingContent?: boolean,
    confirmText?: string,
    cancelText?: string,
    okText?: string,
    closeOnFinish?: {
      close: boolean
    },
    showCloseOnError?: {
      show: boolean
    },
    showConfirmOnSuccess?: {
      show: boolean
    },
    closeText?: string,
    loadingList?: {
      name: string,
      loading: 0 | 1 | 2 | 3 | 4
    }[],
    startSending: () => void
  } ) {
  }

  ngOnInit(): void {
  }

  public startSending(): void {
    this.data.loadingContent = true;

    setTimeout( () => {
      this.data.startSending();
    }, 800 );
  }
}
