import { MAT_DIALOG_DEFAULT_OPTIONS } from '@angular/material/dialog';

export const MatDialogConfigService = {
  provide: MAT_DIALOG_DEFAULT_OPTIONS,
  useValue: { hasBackdrop: true }
};
