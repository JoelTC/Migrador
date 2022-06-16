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
	public DocAplicacion migrarAplicacion(String version, String tipo) {
		try {

			serializer = new Persister();

			// Deserializa el archivo y lo conviete en el objeto aplicacion
			DocAplicacion docAplicacion = serializer.read(DocAplicacion.class, FileServiceImpl.archivo);

			switch (version) {
			case "2.1":
				docAplicacion = convertirVersion21(docAplicacion, tipo);
				break;

			case "2.3":
				docAplicacion = convertirVersion23(docAplicacion, tipo);
				break;

			}

			gestionarArchivos(docAplicacion);
			return docAplicacion;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}
	
	private DocAplicacion convertirVersion21(DocAplicacion docAplicacion, String tipo) {

		for (Rol rol : docAplicacion.getAplicacion().getlRol()) {
			rol.setTipo(tipo);
		}

		return convertirVersion23(docAplicacion, tipo);
	}

	private DocAplicacion convertirVersion23(DocAplicacion docAplicacion, String tipo) {

		for (Permiso permiso : docAplicacion.getAplicacion().getlPermiso()) {
			permiso.setEstado("HABILITADO");
			permiso.setTipo(tipo);
			permiso.setTipoOpc(null);
			permiso.setTipoOpcion(tipo);
			permiso.setDataSeg(null);
			permiso.setConfiguracion("");
		}

		return docAplicacion;
	}

	private void gestionarArchivos(DocAplicacion docAplicacion) throws Exception {

		if (FileServiceImpl.archivo.exists()) {
			FileServiceImpl.archivo.delete();
		}

		formato = new Format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); // Formato para el documento xml
		serializer = new Persister(formato);
		File result = new File(FileServiceImpl.rutaFolder + "\\" + FileServiceImpl.nombreArchivo);
		serializer.write(docAplicacion, result);
	}

}
