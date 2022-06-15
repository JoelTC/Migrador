package com.novatronic.pscabas.gt.webcore.services.implement;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.springframework.stereotype.Service;

import com.novatronic.pscabas.gt.webcore.domains.esquema.DocAplicacion;
import com.novatronic.pscabas.gt.webcore.domains.esquema.Permiso;
import com.novatronic.pscabas.gt.webcore.domains.esquema.Rol;
import com.novatronic.pscabas.gt.webcore.services.interfaces.AplicacionMigradorService;

@Service
public class AplicacionMigradorServiceImpl implements AplicacionMigradorService {

	private Serializer serializer;
	private Format formato;

	@Override
	public DocAplicacion migrarAplicacion(String tipo, String version) {
		try {

			serializer = new Persister();

			// Deserializa el archivo y lo conviete en el objeto aplicacion
			DocAplicacion aplicacion = serializer.read(DocAplicacion.class, FileServiceImpl.archivo);
			System.out.println(version);

			switch (version) {
			case "2.1":
				System.out.println("Tipo: " + tipo + "Version: " + version);
				aplicacion = convertirTipoVersion21(aplicacion, tipo);
				break;
				
			case "2.3":
				System.out.println("Tipo: " + tipo + "Version: " + version);
				aplicacion = convertirTipoVersion23(aplicacion, tipo);
				break;
			}

			if (version.equals("2.1")) {
			}

			gestionarArchivos(aplicacion);

			return aplicacion;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	private DocAplicacion convertirTipoVersion21(DocAplicacion aplicacion, String tipo) {

		for (Rol rol : aplicacion.getAplicacion().getlRol()) {
			rol.setTipo(tipo);
		}

		return convertirTipoVersion23(aplicacion, tipo);
	}

	private DocAplicacion convertirTipoVersion23(DocAplicacion aplicacion, String tipo) {

		for (Permiso permiso : aplicacion.getAplicacion().getlPermiso()) {
			permiso.setEstado("HABILITADO");
			permiso.setTipo(tipo);
			permiso.setTipoOpc(null);
			permiso.setTipoOpcion(tipo);
			permiso.setDataSeg(null);
			permiso.setConfiguracion("");
		}

		return aplicacion;
	}

	private void gestionarArchivos(DocAplicacion aplicacion) throws Exception {

		if (FileServiceImpl.archivo.exists()) {
			FileServiceImpl.archivo.delete();
		}

		formato = new Format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); // Formato para el documento xml
		serializer = new Persister(formato);
		File result = new File(FileServiceImpl.rutaFolder + "\\" + FileServiceImpl.nombreArchivo);
		serializer.write(aplicacion, result);
	}

}
