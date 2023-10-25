import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from '../../environments/environment';
/* Models */
import { UserListByOfficeModel } from'../models/user.list.by.office.model';
import { OfficeListModel } from'../models/office.list.model';

@Injectable({
  providedIn: 'root'
})
export class OfficeListService {

  constructor(private http: HttpClient) { }

  /* Consulta la lista de usuarios vinculados a una oficina específica y filtrando por el estado Activo */
  public getUserListByOfficeActive( office: string, state: string): Observable<{ result: UserListByOfficeModel[] }> {
    return this.http.get<{ result: UserListByOfficeModel[] }>( environment.api.get_active_user_list_by_office, {      
      params: { office, state }
    } );
  }

  /* Creación de un nuevo usuario vinculado a la oficina */
  public registerUserByOffice(data: UserListByOfficeModel): Observable<{ result: string}> {
    return this.http.post<{ result: string }>( environment.api.post_user_by_office_reg, data);
  }

  /* Elimina la infomación del usuario asociado a la oficina */
  public deleteUserOfficeList(id: string): Observable<{ result: string }> {
    return this.http.delete<{ result: string }>( environment.api.delete_user_office_list.replace(':id', id), {});
  }

  /* Consulta la información del usuario mediante su id o item */
  public getUserItemListOffice(id: string): Observable<{ result: UserListByOfficeModel }> {
    return this.http.get<{ result: UserListByOfficeModel }>( environment.api.getUserItemOfficeList.replace(':id', id), {});
  }

  /* Actualiza los datos del usuario en la lista de oficinas */
  public editUserListOffice(id: string, data: UserListByOfficeModel): Observable<{ result: string }> {
    return this.http.put<{ result: string }>( environment.api.editUserListOffice.replace(':id', id), data);
  }

  /* Consulta la lista de oficinas por su estado */
  public getOfficeList(status: string): Observable<{ result: OfficeListModel[] }> {
    return this.http.get<{ result: OfficeListModel[] }>( environment.api.get_office_list, {
      params: { status }
    } );  
  }

}
