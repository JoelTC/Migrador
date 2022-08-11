package com.novatronic.pscabas.gt.webcore.business;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

import com.novatronic.pscabas.gt.webcore.domains.entities.AplicacionDTO;
import com.novatronic.pscabas.gt.webcore.domains.entities.AplicacionUsuario;
import com.novatronic.pscabas.gt.webcore.domains.esquema.Aplicacion;
import com.novatronic.pscabas.gt.webcore.domains.esquema.DocEmpresa;
import com.novatronic.pscabas.gt.webcore.domains.esquema.Permiso;
import com.novatronic.pscabas.gt.webcore.domains.esquema.Rol;
import com.novatronic.pscabas.gt.webcore.domains.esquema.RolPadre;
import com.novatronic.pscabas.gt.webcore.domains.esquema.RolPorRol;
import com.novatronic.pscabas.gt.webcore.domains.esquema.Usuario;
import com.novatronic.pscabas.gt.webcore.domains.esquema.UsuarioRol;
import com.novatronic.pscabas.gt.webcore.domains.request.MigradorEmpresaRequest;
import com.novatronic.pscabas.gt.webcore.domains.request.RolPadreRequest;
import com.novatronic.pscabas.gt.webcore.exceptios.MigradorException;
import com.novatronic.pscabas.gt.webcore.services.implement.FileServiceImpl;
import com.novatronic.pscabas.gt.webcore.util.Constantes;

public class EmpresaBusiness {
	private Serializer serializer;
	private Format formato;

	public DocEmpresa convertirVersion21(DocEmpresa pDocEmpresa, MigradorEmpresaRequest pMigradorEmpresa,
			List<AplicacionUsuario> plAplicacionUsuario) throws MigradorException {
		try {
			for (Aplicacion iAplicacion : pDocEmpresa.getlAplicacion()) {
				if (iAplicacion.getlRol() != null) {
					for (Rol iRol : iAplicacion.getlRol()) {
						iRol.setTipo(pMigradorEmpresa.getTipo());
					}
				}
			}

			return convertirVersion23(pDocEmpresa, pMigradorEmpresa, plAplicacionUsuario);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	public DocEmpresa convertirVersion23(DocEmpresa pDocEmpresa, MigradorEmpresaRequest pMigradorEmpresa,
			List<AplicacionUsuario> plAplicacionUsuario) throws MigradorException {
		try {
			for (Usuario iUsuario : pDocEmpresa.getlUsuario()) {
				if (iUsuario.getFechaNacimiento() == null || iUsuario.getFechaNacimiento().equals("")) {
					iUsuario.setFechaNacimiento(pMigradorEmpresa.getFechaNacimiento());
				}
			}

			for (Aplicacion iAplicacion : pDocEmpresa.getlAplicacion()) {
				if (iAplicacion.getlPermiso() != null) {
					for (Permiso iPermiso : iAplicacion.getlPermiso()) {
						iPermiso.setEstado(Constantes.ESTADO_HABILITADO);
						iPermiso.setTipo(pMigradorEmpresa.getTipo());
						iPermiso.setTipoOpc(null);
						iPermiso.setTipoOpcion(pMigradorEmpresa.getTipo());
						iPermiso.setDataSeg(null);
						iPermiso.setConfiguracion("");
					}
				}
			}

			pDocEmpresa = agregarAplicacionUsuario(pDocEmpresa, plAplicacionUsuario);

			pDocEmpresa = filtrarUsuarioRol(pDocEmpresa, pMigradorEmpresa.getTipo());// agregarRolPadre(pDocEmpresa,
																						// pTipo);

			// Funciones extras
			pDocEmpresa = filtrarAplicacion(pDocEmpresa, pMigradorEmpresa.getlAplicacion());

			pDocEmpresa = migrarCifrado(pDocEmpresa, pMigradorEmpresa.getCifradoOrigen(),
					pMigradorEmpresa.getCifradoDestino(), pMigradorEmpresa.getContrasena());

			// pDocEmpresa = renombrarRolPadre(pDocEmpresa,
			// pMigradorEmpresa.getlRolPadre());

			return pDocEmpresa;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	public List<AplicacionDTO> listarAplicacion(DocEmpresa pDocEmpresa) throws MigradorException {
		try {
			List<AplicacionDTO> lAplicacion = new ArrayList<AplicacionDTO>();

			for (Aplicacion iAplicacion : pDocEmpresa.getlAplicacion()) {
				AplicacionDTO oAplicacion = new AplicacionDTO();
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

	public DocEmpresa filtrarAplicacion(DocEmpresa pDocEmpresa, List<AplicacionDTO> pAplicacion)
			throws MigradorException {
		try {
			if (pAplicacion != null && pAplicacion.size() > 0) {
				List<Aplicacion> lAplicacion = new ArrayList<Aplicacion>();
				List<RolPadre> lRolPadre = new ArrayList<RolPadre>();

				for (Aplicacion iAplicacion : pDocEmpresa.getlAplicacion()) {
					for (AplicacionDTO iAplicacionR : pAplicacion) {
						if (iAplicacion.getMnemonico().equals(iAplicacionR.getMnemonico())) {
							lAplicacion.add(iAplicacion);
							break;
						}
					}
				}

				pDocEmpresa.setlAplicacion(null);
				pDocEmpresa.setlAplicacion(lAplicacion);

				for (RolPadre iRolPadre : pDocEmpresa.getlRolPadre()) {
					RolPadre oRolPadre = new RolPadre();
					List<RolPorRol> lRolPorRol = new ArrayList<RolPorRol>();
					if (iRolPadre.getlRolPorRol() != null) {
						for (RolPorRol iRolPorRol : iRolPadre.getlRolPorRol()) {
							for (Aplicacion iAplicacion : pDocEmpresa.getlAplicacion()) {
								if (iRolPorRol.getMnemonicoaplicacion().equals(iAplicacion.getMnemonico())) {
									lRolPorRol.add(iRolPorRol);
								}
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

				pDocEmpresa.setlRolPadre(null);
				pDocEmpresa.setlRolPadre(lRolPadre);

				List<Usuario> lUsuario = new ArrayList<Usuario>();

				for (RolPadre iRolPadre : pDocEmpresa.getlRolPadre()) {
					for (Usuario iUsuario : pDocEmpresa.getlUsuario()) {
						if (iUsuario.getRolMnemonico() != null
								&& iUsuario.getRolMnemonico().equals(iRolPadre.getMnemonico())) {
							lUsuario.add(iUsuario);
						}
					}
				}

				for (Usuario iUsuario : pDocEmpresa.getlUsuario()) {
					if (iUsuario.getRolMnemonico() == null) {
						lUsuario.add(iUsuario);
					}
				}

				pDocEmpresa.setlUsuario(null);
				pDocEmpresa.setlUsuario(lUsuario);

				return filtrarRolAgrupador(pDocEmpresa);
			} else {
				return pDocEmpresa;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	public DocEmpresa migrarCifrado(DocEmpresa pDocEmpresa, String pCifradoO, String pCifradoD, String pPass)
			throws MigradorException {
		try {
			if ((pCifradoO != null || pCifradoO != null) && pCifradoO.equals("0") && pCifradoD.equals("1")) {
				pDocEmpresa.setCifrado(pCifradoD);
				for (Usuario iUsuario : pDocEmpresa.getlUsuario()) {
					iUsuario.setContrasena(pPass);
				}
				return pDocEmpresa;
			} else {
				return pDocEmpresa;
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
			if (pRolPadre != null) {
				for (RolPadreRequest iRolPadreReq : pRolPadre) {
					for (RolPadre iRolPadre : pDocEmpresa.getlRolPadre()) {
						if (iRolPadre.getMnemonico().equals(iRolPadreReq.getMnemonicoOrigen())) {
							iRolPadre.setNombre(iRolPadreReq.getMnemonicoDestino());
							iRolPadre.setMnemonico(iRolPadreReq.getMnemonicoDestino());
							break;
						}
					}
					for (Usuario iUsuario : pDocEmpresa.getlUsuario()) {
						if (iUsuario.getRolMnemonico() != null
								&& iUsuario.getRolMnemonico().equals(iRolPadreReq.getMnemonicoOrigen())) {
							iUsuario.setRolMnemonico(iRolPadreReq.getMnemonicoDestino());
						}
					}
				}

				return pDocEmpresa;
			} else {
				return pDocEmpresa;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	private DocEmpresa agregarAplicacionUsuario(DocEmpresa pDocEmpresa, List<AplicacionUsuario> plAplicacionUsuario)
			throws MigradorException {

		Collections.sort(pDocEmpresa.getlUsuario(), (x, y) -> x.getUsuario().compareToIgnoreCase(y.getUsuario()));
		for (Usuario iUsuario : pDocEmpresa.getlUsuario()) {
			if (iUsuario.getlUsuarioRol() != null) {
				Collections.sort(iUsuario.getlUsuarioRol(),
						(x, y) -> x.getMnemonico().compareToIgnoreCase(y.getMnemonico()));
			}
		}

		for (Usuario iUsuario : pDocEmpresa.getlUsuario()) {
			if (iUsuario.getlUsuarioRol() != null) {
				List<AplicacionUsuario> lAplicionUsuario = new ArrayList<AplicacionUsuario>();
				for (AplicacionUsuario iAplicacionUsuario : plAplicacionUsuario) {
					if (iUsuario.getUsuario().equals(iAplicacionUsuario.getUsuario())) {
						lAplicionUsuario.add(iAplicacionUsuario);
					}
				}

				if (iUsuario.getlUsuarioRol().size() == lAplicionUsuario.size()) {
					for (UsuarioRol iUsuarioRol : iUsuario.getlUsuarioRol()) {
						int i = 0;
						for (AplicacionUsuario iAplicacionUsuario : lAplicionUsuario) {
							if (iUsuarioRol.getMnemonico().equals(iAplicacionUsuario.getRol())) {
								iUsuarioRol.setAplicacion(iAplicacionUsuario.getAplicacion());
								lAplicionUsuario.remove(i);
								break;
							}
							i++;
						}
					}
				}
			}
		}

		return pDocEmpresa;
	}

	private DocEmpresa filtrarUsuarioRol(DocEmpresa pDocEmpresa, String pTipo) throws MigradorException {
		try {

			for (Usuario iUsuario : pDocEmpresa.getlUsuario()) {
				List<UsuarioRol> lUsuarioRol = new ArrayList<UsuarioRol>();
				boolean a = false;
				if (iUsuario.getlUsuarioRol() != null) {
					for (UsuarioRol iUsuarioRol : iUsuario.getlUsuarioRol()) {
						if (lUsuarioRol.size() > 0) {
							for (UsuarioRol iURol : lUsuarioRol) {
								if (!iURol.toString().equals(iUsuarioRol.toString())) {
									a = true;
								} else {
									a = false;
									break;
								}
							}
							if (a) {
								lUsuarioRol.add(iUsuarioRol);
							}
						} else {
							lUsuarioRol.add(iUsuarioRol);
						}
					}
					iUsuario.setlUsuarioRol(null);
					iUsuario.setlUsuarioRol(lUsuarioRol);
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
								oRolPorRol.setMnemonico(iUsuarioRol.getMnemonico());
								oRolPorRol.setMnemonicoaplicacion(iUsuarioRol.getAplicacion());
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
							oRolPorRol.setMnemonico(iUsuarioRol.getMnemonico());
							oRolPorRol.setMnemonicoaplicacion(iUsuarioRol.getAplicacion());
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
							break;
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

	private DocEmpresa filtrarRolAgrupador(DocEmpresa pDocEmpresa) throws MigradorException {
		try {
			List<RolPadre> lRolPadre = new ArrayList<RolPadre>();
			boolean a = false;
			for (RolPadre iRolPadre : pDocEmpresa.getlRolPadre()) {
				if (lRolPadre.size() > 0) {
					for (RolPadre ilRolPadre : lRolPadre) {
						if (iRolPadre.getlRolPorRol().toString().equals(ilRolPadre.getlRolPorRol().toString())) {
							a = false;
							for (Usuario iUsuario : pDocEmpresa.getlUsuario()) {
								if (iUsuario.getRolMnemonico() != null
										&& iUsuario.getRolMnemonico().equals(iRolPadre.getMnemonico())) {
									iUsuario.setRolMnemonico(ilRolPadre.getMnemonico());
								}
							}
							break;
						} else {
							a = true;
						}
					}

					if (a) {
						lRolPadre.add(iRolPadre);
					}
				} else {
					lRolPadre.add(iRolPadre);
				}
			}

			pDocEmpresa.setlRolPadre(null);
			pDocEmpresa.setlRolPadre(lRolPadre);
			return pDocEmpresa;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}

	}

	public void gestionarArchivos(DocEmpresa pDocEmpresa) throws MigradorException {
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
