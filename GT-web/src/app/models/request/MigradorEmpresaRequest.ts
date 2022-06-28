import { AplicacionDTO } from "../entities/AplicacionDTO";

export class MigradorEmpresaRequest {
    version: string;
    rolPadre: string;
    tipo: string;
    lAplicacion: AplicacionDTO[];
    fechaNacimiento: string;
    cifradoOrigen: string;
    cifradoDestino: string;
    contrasena: string;
}