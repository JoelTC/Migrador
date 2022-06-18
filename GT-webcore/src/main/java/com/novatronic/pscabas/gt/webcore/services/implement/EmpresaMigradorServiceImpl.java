package com.novatronic.pscabas.gt.webcore.services.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.springframework.stereotype.Service;

import com.novatronic.pscabas.gt.webcore.domains.esquema.Aplicacion;
import com.novatronic.pscabas.gt.webcore.domains.esquema.DocEmpresa;
import com.novatronic.pscabas.gt.webcore.domains.esquema.Permiso;
import com.novatronic.pscabas.gt.webcore.domains.esquema.Rol;
import com.novatronic.pscabas.gt.webcore.domains.esquema.RolPadre;
import com.novatronic.pscabas.gt.webcore.domains.esquema.RolPorRol;
import com.novatronic.pscabas.gt.webcore.domains.esquema.Usuario;
import com.novatronic.pscabas.gt.webcore.domains.esquema.UsuarioRol;
import com.novatronic.pscabas.gt.webcore.domains.request.MigradorEmpresa;
import com.novatronic.pscabas.gt.webcore.services.interfaces.EmpresaMigradorService;

@Service
public class EmpresaMigradorServiceImpl implements EmpresaMigradorService {

	private Serializer serializer;
	private Format formato;

	// String version, String rolPadre, String tipo
	@Override
	public DocEmpresa migrarEmpresa(MigradorEmpresa mEmpresa) {
		try {
			serializer = new Persister();

			// Deserializa el archivo y lo conviete en el objeto empresa
			DocEmpresa docEmpresa = serializer.read(DocEmpresa.class, FileServiceImpl.archivo);

			switch (mEmpresa.getVersion()) {
			case "2.1":
				docEmpresa = convertirVersion21(docEmpresa, mEmpresa.getTipo(), mEmpresa.getFechaNacimiento(),
						mEmpresa.getRolPadre());
				break;

			case "2.3":
				docEmpresa = convertirVersion23(docEmpresa, mEmpresa.getTipo(), mEmpresa.getRolPadre());
				break;

			}

			gestionarArchivos(docEmpresa);
			return docEmpresa;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	@Override
	public MigradorEmpresa listarAplicacion() {
		try {

			List<Aplicacion> lAplicacion = new ArrayList<Aplicacion>();
			MigradorEmpresa mEmpresa = new MigradorEmpresa();
			serializer = new Persister();

			// Deserializa el archivo y lo conviete en el objeto aplicacion
			DocEmpresa docEmpresa = serializer.read(DocEmpresa.class, FileServiceImpl.archivo);

			for (Aplicacion app : docEmpresa.getlAplicacion()) {
				Aplicacion aplicacion = new Aplicacion();

				aplicacion.setNombre(app.getNombre());

				lAplicacion.add(aplicacion);
			}

			mEmpresa.setlAplicacion(lAplicacion);
			return mEmpresa;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	private DocEmpresa convertirVersion21(DocEmpresa docEmpresa, String tipo, String fecha, String rolPadre) {
		for (Aplicacion app : docEmpresa.getlAplicacion()) {
			if (app.getlRol() != null) {
				for (Rol rol : app.getlRol()) {
					rol.setTipo(tipo);
				}
			}
		}

		for (Usuario user : docEmpresa.getlUsuario()) {
			user.setFechaNacimiento(fecha);
		}

		return convertirVersion23(docEmpresa, tipo, rolPadre);
	}

	private DocEmpresa convertirVersion23(DocEmpresa docEmpresa, String tipo, String rolPadre) {

		for (Aplicacion app : docEmpresa.getlAplicacion()) {
			if (app.getlPermiso() != null) {
				for (Permiso permiso : app.getlPermiso()) {
					permiso.setEstado("HABILITADO");
					permiso.setTipo(tipo);
					permiso.setTipoOpc(null);
					permiso.setTipoOpcion(tipo);
					permiso.setDataSeg(null);
					permiso.setConfiguracion("");
				}
			}
		}

		return agregarRolPadre(docEmpresa, tipo);
	}

	private DocEmpresa agregarRolPadre(DocEmpresa docEmpresa, String tipo) {

		// Lógica para registrar el lemento USUARIOROL en el elemento ROLPORROL
		List<RolPadre> lRolPadre = new ArrayList<RolPadre>();
		int i = 1;
		for (Usuario user : docEmpresa.getlUsuario()) {
			List<RolPorRol> lrolXrol = new ArrayList<RolPorRol>();
			boolean a = false;
			if (lRolPadre.size() > 0) {
				for (RolPadre rp : lRolPadre) {
					if (user.getlUsuarioRol() != null
							&& !rp.getlRolPorRol().toString().equals(user.getlUsuarioRol().toString())) {
						a = true;
					} else {
						a = false;
						break;
					}
				}

				if (a) {
					RolPadre rolPadre = new RolPadre();
					if (user.getlUsuarioRol() != null) {

						for (UsuarioRol uRol : user.getlUsuarioRol()) {
							RolPorRol rolXrol = new RolPorRol();

							rolXrol.setNombre(uRol.getNombre());
							rolXrol.setMnemonico(uRol.getmnemonico());
							lrolXrol.add(rolXrol);
						}

						rolPadre.setNombre("RolAgrupador_" + i);
						rolPadre.setMnemonico("RolAgrupador_" + i);
						rolPadre.setTipo(tipo);

						rolPadre.setlRolPorRol(lrolXrol);
						lRolPadre.add(rolPadre);
						i++;
					}
				}
			} else {
				RolPadre rolPadre = new RolPadre();
				if (user.getlUsuarioRol() != null) {
					for (UsuarioRol uRol : user.getlUsuarioRol()) {
						RolPorRol rolXrol = new RolPorRol();

						rolXrol.setNombre(uRol.getNombre());
						rolXrol.setMnemonico(uRol.getmnemonico());
						lrolXrol.add(rolXrol);
					}
					rolPadre.setNombre("RolAgrupador_" + i);
					rolPadre.setMnemonico("RolAgrupador_" + i);
					rolPadre.setTipo(tipo);

					rolPadre.setlRolPorRol(lrolXrol);
					lRolPadre.add(rolPadre);
					i++;
				}
			}
		}

		// Se setea la lista de ROLPADRE al documento
		docEmpresa.setlRolPadre(lRolPadre);
		return agregarRolMnemonico(docEmpresa);
	}

	private DocEmpresa agregarRolMnemonico(DocEmpresa docEmpresa) {
		// Se le asigna un rol agrupador a los usuarios de acuerto al usuario rol
		for (Usuario user : docEmpresa.getlUsuario()) {
			if (user.getlUsuarioRol() != null) {
				for (RolPadre rPadre : docEmpresa.getlRolPadre()) {
					if (user.getlUsuarioRol().toString().equals(rPadre.getlRolPorRol().toString())) {
						user.setRolMnemonico(rPadre.getMnemonico());
					}
				}
				user.setlUsuarioRol(null);
			}
		}
		return docEmpresa;
	}

	private void gestionarArchivos(DocEmpresa empresa) throws Exception {

		// Se elimina el archivo de respaldo para guardad el final
		if (FileServiceImpl.archivo.exists()) {
			FileServiceImpl.archivo.delete();
		}

		// Etiqueta superior del documento
		formato = new Format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); // Formato para el documento xml
		serializer = new Persister(formato);
		File result = new File(FileServiceImpl.rutaFolder + "\\" + FileServiceImpl.nombreArchivo);
		serializer.write(empresa, result);
	}
}
