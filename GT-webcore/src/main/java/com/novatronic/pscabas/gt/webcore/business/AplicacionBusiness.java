package com.novatronic.pscabas.gt.webcore.business;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

import com.novatronic.pscabas.gt.webcore.domains.esquema.Aplicacion;
import com.novatronic.pscabas.gt.webcore.domains.esquema.DocAplicacion;
import com.novatronic.pscabas.gt.webcore.domains.esquema.Permiso;
import com.novatronic.pscabas.gt.webcore.domains.esquema.Rol;
import com.novatronic.pscabas.gt.webcore.exception.MigradorException;
import com.novatronic.pscabas.gt.webcore.services.implement.FileServiceImpl;
import com.novatronic.pscabas.gt.webcore.util.Constantes;

public class AplicacionBusiness {
	protected static final Logger logger = LogManager.getLogger(AplicacionBusiness.class);

	private Serializer serializer;
	private Format formato;

	public DocAplicacion convertirVersion21(DocAplicacion pDocAplicacion, String pTipo) throws MigradorException {
		try {
			for (Aplicacion iAplicacion : pDocAplicacion.getlAplicacion()) {
				if (iAplicacion.getlRol() != null) {
					for (Rol iRol : iAplicacion.getlRol()) {
						iRol.setTipo(pTipo);
					}
				}
			}

			return convertirVersion23(pDocAplicacion, pTipo);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public DocAplicacion convertirVersion23(DocAplicacion pDocAplicacion, String pTipo) throws MigradorException {
		try {
			for (Aplicacion iAplicacion : pDocAplicacion.getlAplicacion()) {
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

			gestionarArchivos(pDocAplicacion);

			return pDocAplicacion;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	private void gestionarArchivos(DocAplicacion pDocAplicacion) throws MigradorException {
		try {
			if (FileServiceImpl.archivo.exists()) {
				FileServiceImpl.archivo.delete();
			}

			formato = new Format(Constantes.XML_FORMAT); // Formato para el documento xml
			serializer = new Persister(formato);

			FileServiceImpl.archivo = new File(FileServiceImpl.rutaFolder + "\\" + FileServiceImpl.nombreArchivo);
			serializer.write(pDocAplicacion, FileServiceImpl.archivo);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			logger.error(e.getMessage(), e);
		}
	}
}
