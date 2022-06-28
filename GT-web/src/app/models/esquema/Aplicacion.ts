import { Permiso } from "./Permiso";
import { Rol } from "./Rol";

export class Aplicacion {
    nombre: string;
    mnemonico: string;
    lPermiso: Permiso[];
    lRol: Rol[];
}