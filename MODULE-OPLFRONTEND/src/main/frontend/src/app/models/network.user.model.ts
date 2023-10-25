/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBF-FRONT was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */

export class NetworkUserModel {
  public item: number;
  public usuario: string;
  public nombres: string;
  public apellidos: string;
  public fecha_conexion: string;
  public estado: string;
  public identificacion: string;
  public usuarioXroles: {
    rol: {
      grupoDa: string
    }
  }[] = [];
}
