import { UsuarioRol } from "./UsuarioRol";

export class Usuario {
    nombre: string;
    usuario: string;
    apellidoPaterno: string;
    apellidoMaterno: string;
    fechaNacimiento: string;
    telefono: string;
    tipoDocumento: string;
    numeroDocumento: string;
    correo: string;
    contrasena: string;
    rolMnemonico: string;
    lUsuarioRol: UsuarioRol[];
}