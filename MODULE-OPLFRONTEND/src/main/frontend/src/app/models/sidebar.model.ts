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
import { IconProp } from '@fortawesome/fontawesome-svg-core';

class SidebarItemModel {
    public id: string;
    public title: string;
    public routerLink: string[];
    public icon: IconProp;
    public submenu: {
        id: string,
        title: string,
        routerLink: string[]
    }[];
}

export class SidebarModel {
    public _main: SidebarItemModel[];
    public _admin: SidebarItemModel[];
}
