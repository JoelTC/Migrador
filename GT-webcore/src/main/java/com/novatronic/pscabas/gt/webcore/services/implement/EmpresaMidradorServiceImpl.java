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
public class EmpresaMidradorServiceImpl implements EmpresaMigradorService {

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
			for (Rol rol : app.getlRol()) {
				rol.setTipo(tipo);
			}
		}

		for (Usuario user : docEmpresa.getlUsuario()) {
			user.setFechaNacimiento(fecha);
		}

		return convertirVersion23(docEmpresa, tipo, rolPadre);
	}

	private DocEmpresa convertirVersion23(DocEmpresa docEmpresa, String tipo, String rolPadre) {

		for (Aplicacion app : docEmpresa.getlAplicacion()) {
			for (Permiso permiso : app.getlPermiso()) {
				permiso.setEstado("HABILITADO");
				permiso.setTipo(tipo);
				permiso.setTipoOpc(null);
				permiso.setTipoOpcion(tipo);
				permiso.setDataSeg(null);
				permiso.setConfiguracion("");
			}
		}

		return agregarRolPadre(docEmpresa, tipo);
	}

	private DocEmpresa agregarRolPadre(DocEmpresa docEmpresa, String tipo) {
		String mnemonicoAp;
		List<RolPadre> lRolPadre = new ArrayList<RolPadre>();
		int i = 1;

		for (Usuario user : docEmpresa.getlUsuario()) {
			List<RolPorRol> lrolXrol = new ArrayList<RolPorRol>();

			RolPadre rolPadre = new RolPadre();

			rolPadre.setNombre("RolAgrupador_" + i);
			rolPadre.setMnemonico("RolAgrupador_" + i);
			rolPadre.setTipo(tipo);

			if (user.getlUsuarioRol() != null) {
				for (UsuarioRol uRol : user.getlUsuarioRol()) {
					RolPorRol rolXrol = new RolPorRol();

					rolXrol.setNombre(uRol.getNombre());
					rolXrol.setMnemonico(uRol.getmnemonico());
					lrolXrol.add(rolXrol);
				}

				rolPadre.setlRolPorRol(lrolXrol);
				lRolPadre.add(rolPadre);
				i++;
			}
			
			//user.setlUsuarioRol(null);
		}

		docEmpresa.setlRolPadre(lRolPadre);
		return docEmpresa;
	}

	private void gestionarArchivos(DocEmpresa empresa) throws Exception {

		if (FileServiceImpl.archivo.exists()) {
			FileServiceImpl.archivo.delete();
		}

		formato = new Format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); // Formato para el documento xml
		serializer = new Persister(formato);
		File result = new File(FileServiceImpl.rutaFolder + "\\" + FileServiceImpl.nombreArchivo);
		serializer.write(empresa, result);
	}
}
