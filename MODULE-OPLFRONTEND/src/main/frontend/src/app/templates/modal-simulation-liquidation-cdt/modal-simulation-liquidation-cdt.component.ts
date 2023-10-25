import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-modal-simulation-liquidation-cdt',
  templateUrl: './modal-simulation-liquidation-cdt.component.html',
  styleUrls: ['./modal-simulation-liquidation-cdt.component.scss']
})
export class ModalSimulationLiquidationCdtComponent implements OnInit {

  constructor(
    private dialogRef: MatDialogRef<ModalSimulationLiquidationCdtComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {
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
        loading: 0 | 1 | 2 | 3
      }[],
      startSending: () => void
    }) { }

  ngOnInit(): void {
    this.data.loadingContent = true;
  }

  public startSending(): void {
    this.data.loadingContent = true;
    setTimeout(() => {
      this.data.startSending();
    }, 800);
  }

}
