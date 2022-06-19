package com.novatronic.pscabas.gt.webcore.business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

import com.novatronic.pscabas.gt.webcore.domains.esquema.Aplicacion;
import com.novatronic.pscabas.gt.webcore.domains.esquema.DocEmpresa;
import com.novatronic.pscabas.gt.webcore.domains.esquema.Permiso;
import com.novatronic.pscabas.gt.webcore.domains.esquema.Rol;
import com.novatronic.pscabas.gt.webcore.domains.esquema.RolPadre;
import com.novatronic.pscabas.gt.webcore.domains.esquema.RolPorRol;
import com.novatronic.pscabas.gt.webcore.domains.esquema.Usuario;
import com.novatronic.pscabas.gt.webcore.domains.esquema.UsuarioRol;
import com.novatronic.pscabas.gt.webcore.exceptios.MigradorException;
import com.novatronic.pscabas.gt.webcore.services.implement.FileServiceImpl;
import com.novatronic.pscabas.gt.webcore.util.Constantes;

public class EmpresaBusiness {
	private Serializer serializer;
	private Format formato;

	public DocEmpresa convertirVersion21(DocEmpresa pDocEmpresa, String pTipo, String pFechaNacimiento,
			String pRolPadre) throws MigradorException {
		try {
			for (Aplicacion iAplicacion : pDocEmpresa.getlAplicacion()) {
				if (iAplicacion.getlRol() != null) {
					for (Rol iRol : iAplicacion.getlRol()) {
						iRol.setTipo(pTipo);
					}
				}
			}

			for (Usuario iUsuario : pDocEmpresa.getlUsuario()) {
				iUsuario.setFechaNacimiento(pFechaNacimiento);
			}

			return convertirVersion23(pDocEmpresa, pTipo, pRolPadre);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	public DocEmpresa convertirVersion23(DocEmpresa pDocEmpresa, String pTipo, String pRolPadre)
			throws MigradorException {
		try {
			for (Aplicacion iAplicacion : pDocEmpresa.getlAplicacion()) {
				if (iAplicacion.getlPermiso() != null) {
					for (Permiso iPermiso : iAplicacion.getlPermiso()) {
						iPermiso.setEstado(Constantes.ESTADO_HABILITADO);
						iPermiso.setTipo(pTipo);
						iPermiso.setTipoOpc(null);
						iPermiso.setTipoOpcion(pTipo);
						iPermiso.setDataSeg(null);
						iPermiso.setConfiguracion("");
					}
				}
			}
			pDocEmpresa = agregarRolPadre(pDocEmpresa, pTipo);

			gestionarArchivos(pDocEmpresa);

			return pDocEmpresa;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	private DocEmpresa agregarRolPadre(DocEmpresa pDocEmpresa, String pTipo) throws MigradorException {
		try {
			// Lógica para registrar el lemento USUARIOROL en el elemento ROLPORROL
			List<RolPadre> lRolPadre = new ArrayList<RolPadre>();
			int i = 1;
			for (Usuario iUsuario : pDocEmpresa.getlUsuario()) {
				List<RolPorRol> lRolPorRol = new ArrayList<RolPorRol>();
				boolean a = false;

				if (lRolPadre.size() > 0) {
					for (RolPadre iRolPadre : lRolPadre) {
						if (iUsuario.getlUsuarioRol() != null
								&& !iRolPadre.getlRolPorRol().toString().equals(iUsuario.getlUsuarioRol().toString())) {
							a = true;
						} else {
							a = false;
							break;
						}
					}

					if (a) {
						RolPadre oRolPadre = new RolPadre();
						if (iUsuario.getlUsuarioRol() != null) {

							for (UsuarioRol uRol : iUsuario.getlUsuarioRol()) {
								RolPorRol rolXrol = new RolPorRol();

								rolXrol.setNombre(uRol.getNombre());
								rolXrol.setMnemonico(uRol.getmnemonico());
								lRolPorRol.add(rolXrol);
							}

							oRolPadre.setNombre("RolAgrupador_" + i);
							oRolPadre.setMnemonico("RolAgrupador_" + i);
							oRolPadre.setTipo(pTipo);

							oRolPadre.setlRolPorRol(lRolPorRol);
							lRolPadre.add(oRolPadre);
							i++;
						}
					}
				} else {
					RolPadre rolPadre = new RolPadre();
					if (iUsuario.getlUsuarioRol() != null) {
						for (UsuarioRol uRol : iUsuario.getlUsuarioRol()) {
							RolPorRol rolXrol = new RolPorRol();

							rolXrol.setNombre(uRol.getNombre());
							rolXrol.setMnemonico(uRol.getmnemonico());
							lRolPorRol.add(rolXrol);
						}
						rolPadre.setNombre("RolAgrupador_" + i);
						rolPadre.setMnemonico("RolAgrupador_" + i);
						rolPadre.setTipo(pTipo);

						rolPadre.setlRolPorRol(lRolPorRol);
						lRolPadre.add(rolPadre);
						i++;
					}
				}
			}

			// Se setea la lista de ROLPADRE al documento
			pDocEmpresa.setlRolPadre(lRolPadre);
			return agregarRolMnemonico(pDocEmpresa);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	private DocEmpresa agregarRolMnemonico(DocEmpresa pDocEmpresa) throws MigradorException {

		// Se le asigna un rol agrupador a los usuarios de acuerto al usuario rol
		for (Usuario iUsuario : pDocEmpresa.getlUsuario()) {
			if (iUsuario.getlUsuarioRol() != null) {
				for (RolPadre rPadre : pDocEmpresa.getlRolPadre()) {
					if (iUsuario.getlUsuarioRol().toString().equals(rPadre.getlRolPorRol().toString())) {
						iUsuario.setRolMnemonico(rPadre.getMnemonico());
					}
				}
				iUsuario.setlUsuarioRol(null);
			}
		}

		return pDocEmpresa;
	}

	public List<Aplicacion> listarAplicacion(DocEmpresa pDocEmpresa) throws MigradorException {
		try {
			List<Aplicacion> lAplicacion = new ArrayList<Aplicacion>();

			for (Aplicacion iAplicacion : pDocEmpresa.getlAplicacion()) {
				Aplicacion oAplicacion = new Aplicacion();
				oAplicacion.setNombre(iAplicacion.getNombre());
				lAplicacion.add(oAplicacion);
			}

			return lAplicacion;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	private void gestionarArchivos(DocEmpresa pDocEmpresa) throws MigradorException {
		try {
			// Se elimina el archivo de respaldo para guardad el final
			if (FileServiceImpl.archivo.exists()) {
				FileServiceImpl.archivo.delete();
			}

			// Etiqueta superior del documento
			formato = new Format(Constantes.XML_FORMAT); // Formato para el documento xml
			serializer = new Persister(formato);

			FileServiceImpl.archivo = new File(FileServiceImpl.rutaFolder + "\\" + FileServiceImpl.nombreArchivo);
			serializer.write(pDocEmpresa, FileServiceImpl.archivo);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
		}
	}
}
