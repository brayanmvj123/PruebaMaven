export class SaveOfficeModel{
  public nroOficina: number;
  public cargo: string;
  public nombre: string;
  public correo: string;
  public estadoUsuario: number;
}

export class ResponseSaveOffice{
  public result: {
    status: number;
  }
}
