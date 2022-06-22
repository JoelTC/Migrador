package com.novatronic.pscabas.gt.webcore.business;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
import com.novatronic.pscabas.gt.webcore.domains.request.AplicacionRequest;
import com.novatronic.pscabas.gt.webcore.domains.request.RolPadreRequest;
import com.novatronic.pscabas.gt.webcore.domains.responses.AplicacionResponse;
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

			return convertirVersion23(pDocEmpresa, pTipo, pFechaNacimiento, pRolPadre);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	public DocEmpresa convertirVersion23(DocEmpresa pDocEmpresa, String pTipo, String pFechaNacimiento,
			String pRolPadre) throws MigradorException {
		try {
			for (Usuario iUsuario : pDocEmpresa.getlUsuario()) {
				if (iUsuario.getFechaNacimiento() == null || iUsuario.getFechaNacimiento().equals("")) {
					iUsuario.setFechaNacimiento(pFechaNacimiento);
				}
			}

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
			pDocEmpresa = filtrarRolUsuario(pDocEmpresa, pTipo);// agregarRolPadre(pDocEmpresa, pTipo);

			gestionarArchivos(pDocEmpresa);

			return pDocEmpresa;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	public List<AplicacionResponse> listarAplicacion(DocEmpresa pDocEmpresa) throws MigradorException {
		try {
			List<AplicacionResponse> lAplicacion = new ArrayList<AplicacionResponse>();

			for (Aplicacion iAplicacion : pDocEmpresa.getlAplicacion()) {
				AplicacionResponse oAplicacion = new AplicacionResponse();
				oAplicacion.setNombre(iAplicacion.getNombre());
				oAplicacion.setMnemonico(iAplicacion.getMnemonico());
				lAplicacion.add(oAplicacion);
			}

			return lAplicacion;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	public DocEmpresa filtrarAplicacion(DocEmpresa pDocEmpresa, List<AplicacionRequest> pAplicacion)
			throws MigradorException {
		try {
			List<Aplicacion> lAplicacion = new ArrayList<Aplicacion>();
			List<RolPadre> lRolPadre = new ArrayList<RolPadre>();

			for (Aplicacion iAplicacion : pDocEmpresa.getlAplicacion()) {
				for (AplicacionRequest iAplicacionR : pAplicacion) {
					if (iAplicacion.getMnemonico().equals(iAplicacionR.getMnemonico())) {
						lAplicacion.add(iAplicacion);
						break;
					}
				}
			}

			pDocEmpresa.setlAplicacion(null);
			pDocEmpresa.setlAplicacion(lAplicacion);

			for (Aplicacion iAplicacion : pDocEmpresa.getlAplicacion()) {
				for (RolPadre iRolPadre : pDocEmpresa.getlRolPadre()) {
					RolPadre oRolPadre = new RolPadre();
					List<RolPorRol> lRolPorRol = new ArrayList<RolPorRol>();
					if (iRolPadre.getlRolPorRol() != null) {
						for (RolPorRol iRolPorRol : iRolPadre.getlRolPorRol()) {
							if (iRolPorRol.getMnemonicoaplicacion().equals(iAplicacion.getMnemonico())) {
								lRolPorRol.add(iRolPorRol);
							}
						}
						if (lRolPorRol.size() > 0) {
							oRolPadre.setlRolPorRol(lRolPorRol);
							oRolPadre.setMnemonico(iRolPadre.getMnemonico());
							oRolPadre.setNombre(iRolPadre.getNombre());
							oRolPadre.setTipo(iRolPadre.getTipo());
							lRolPadre.add(oRolPadre);
						}
					}
				}
			}

			pDocEmpresa.setlRolPadre(null);
			pDocEmpresa.setlRolPadre(lRolPadre);

			List<Usuario> lUsuario = new ArrayList<Usuario>();

			for (RolPadre iRolPadre : pDocEmpresa.getlRolPadre()) {
				for (Usuario iUsuario : pDocEmpresa.getlUsuario()) {
					if (iUsuario.getRolMnemonico().equals(iRolPadre.getMnemonico())) {
						lUsuario.add(iUsuario);
					}
				}
			}

			pDocEmpresa.setlUsuario(null);
			pDocEmpresa.setlUsuario(lUsuario);

			gestionarArchivos(pDocEmpresa);
			return pDocEmpresa;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	public DocEmpresa migrarCifrado(DocEmpresa pDocEmpresa, String pCifradoO, String pCifradoD, String pPass)
			throws MigradorException {
		try {
			if (pCifradoO.equals("0") && pCifradoD.equals("1")) {
				for (Usuario iUsuario : pDocEmpresa.getlUsuario()) {
					iUsuario.setContrasena(pPass);
				}
				gestionarArchivos(pDocEmpresa);
				return pDocEmpresa;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	public DocEmpresa renombrarRolPadre(DocEmpresa pDocEmpresa, List<RolPadreRequest> pRolPadre)
			throws MigradorException {
		try {

			if (pDocEmpresa.getlRolPadre().size() == pRolPadre.size()) {
				for (int i = 0; i < pDocEmpresa.getlRolPadre().size(); i++) {
					pDocEmpresa.getlRolPadre().get(i).setNombre(pRolPadre.get(i).getMnemonicoDestino());
					pDocEmpresa.getlRolPadre().get(i).setMnemonico(pRolPadre.get(i).getMnemonicoDestino());

					for (Usuario iUsuario : pDocEmpresa.getlUsuario()) {
						if (iUsuario.getRolMnemonico().equals(pRolPadre.get(i).getMnemonicoOrigen())) {
							iUsuario.setRolMnemonico(pRolPadre.get(i).getMnemonicoDestino());
						}
					}
				}
				gestionarArchivos(pDocEmpresa);
				return pDocEmpresa;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	private DocEmpresa filtrarRolUsuario(DocEmpresa pDocEmpresa, String pTipo) throws MigradorException {
		try {
			for (Usuario iUsuario : pDocEmpresa.getlUsuario()) {
				if (iUsuario.getlUsuarioRol() != null) {
					Collections.sort(iUsuario.getlUsuarioRol(),
							(x, y) -> x.getmnemonico().compareToIgnoreCase(y.getmnemonico()));
				}
			}
			return agregarRolPadre(pDocEmpresa, pTipo);
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

							for (UsuarioRol iUsuarioRol : iUsuario.getlUsuarioRol()) {
								RolPorRol oRolPorRol = new RolPorRol();

								oRolPorRol.setNombre(iUsuarioRol.getNombre());
								oRolPorRol.setMnemonico(iUsuarioRol.getmnemonico());
								lRolPorRol.add(oRolPorRol);
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
					RolPadre oRolPadre = new RolPadre();
					if (iUsuario.getlUsuarioRol() != null) {
						for (UsuarioRol iUsuarioRol : iUsuario.getlUsuarioRol()) {
							RolPorRol oRolPorRol = new RolPorRol();

							oRolPorRol.setNombre(iUsuarioRol.getNombre());
							oRolPorRol.setMnemonico(iUsuarioRol.getmnemonico());
							lRolPorRol.add(oRolPorRol);
						}
						oRolPadre.setNombre("RolAgrupador_" + i);
						oRolPadre.setMnemonico("RolAgrupador_" + i);
						oRolPadre.setTipo(pTipo);

						oRolPadre.setlRolPorRol(lRolPorRol);
						lRolPadre.add(oRolPadre);
						i++;
					}
				}
			}

			// Se setea la lista de ROLPADRE al documento
			pDocEmpresa.setlRolPadre(lRolPadre);
			return agregarMnemonicoAplicacion(pDocEmpresa);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	private DocEmpresa agregarMnemonicoAplicacion(DocEmpresa pDocEmpresa) throws MigradorException {
		try { /*
				 * for (RolPadre iRolPadre : pDocEmpresa.getlRolPadre()) { for (RolPorRol
				 * iRolPorRol : iRolPadre.getlRolPorRol()) { // System.out.println("RolPorRol: "
				 * + iRolPorRol.getMnemonico());
				 * 
				 * 
				 * 
				 * for (Aplicacion iAplicacion : pDocEmpresa.getlAplicacion()) { if
				 * (iAplicacion.getlRol() != null) { //List<String> lAplicaciones = null; for
				 * (Rol iRol : iAplicacion.getlRol()) { // System.out.println(" Rol: " +
				 * iRol.getMnemonico()); if
				 * (iRolPorRol.getMnemonico().toString().equals(iRol.getMnemonico().toString()))
				 * { // System.out.println(iRolPorRol.toString() + " --> " + //
				 * iAplicacion.getMnemonico()); //lAplicaciones.add(iAplicacion.getMnemonico());
				 * iRolPorRol.setMnemonicoaplicacion(iAplicacion.getMnemonico()); } } }
				 * 
				 * } //System.out.println("---------------------------"); }
				 * //System.out.println("==========================================="); }
				 */
			return agregarRolMnemonico(pDocEmpresa);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	private DocEmpresa agregarRolMnemonico(DocEmpresa pDocEmpresa) throws MigradorException {
		try {
			// Se le asigna un rol agrupador a los usuarios de acuerto al usuario rol
			for (Usuario iUsuario : pDocEmpresa.getlUsuario()) {
				if (iUsuario.getlUsuarioRol() != null) {
					for (RolPadre iRolPadre : pDocEmpresa.getlRolPadre()) {
						if (iUsuario.getlUsuarioRol().toString().equals(iRolPadre.getlRolPorRol().toString())) {
							iUsuario.setRolMnemonico(iRolPadre.getMnemonico());
						}
					}
					iUsuario.setlUsuarioRol(null);
				}
			}

			return pDocEmpresa;
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
