import { Aplicacion } from "./Aplicacion";
import { RolPadre } from "./RolPadre";
import { Usuario } from "./Usuario";

export class DocEmpresa {
    nombre: string;
    mnemonico: string;
    cifrado: string;
    lAplicacion: Aplicacion[];
    lRolPadre: RolPadre[];
    lUsuario: Usuario[];
}