import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { ExcelModel } from '../models/excel.model';

@Injectable( {
  providedIn: 'root'
} )
export class FileService {

  constructor( private http: HttpClient ) {
  }

  /**
   * Download Excel File Report.
   * Download Blob File (xlsx file).
   */
  downloadExcel( data: ExcelModel ): Promise<Blob> {
    return this.http.post<Blob>( environment.api.download_user_excel, data, {responseType: 'blob' as 'json'} ).toPromise();
  }
}
