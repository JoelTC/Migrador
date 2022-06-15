package com.novatronic.pscabas.gt.webcore.services.implement;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.springframework.stereotype.Service;

import com.novatronic.pscabas.gt.webcore.domains.esquema.DocEmpresa;
import com.novatronic.pscabas.gt.webcore.services.interfaces.EmpresaMigradorService;

@Service
public class EmpresaMidradorServiceImpl implements EmpresaMigradorService {

	private Serializer serializer;
	private Format formato;

	@Override
	public DocEmpresa migrarEmpresa() {
		try {
			serializer = new Persister();
			
			// Deserializa el archivo y lo conviete en el objeto empresa
			DocEmpresa empresa = serializer.read(DocEmpresa.class, FileServiceImpl.archivo); 
			gestionarArchivos(empresa);
			return empresa;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	private void gestionarArchivos(DocEmpresa empresa) throws Exception {

		if(FileServiceImpl.archivo.exists()) {
			FileServiceImpl.archivo.delete();
		}
		
		formato = new Format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); // Formato para el documento xml
		serializer = new Persister(formato);
		File result = new File(FileServiceImpl.rutaFolder + "\\" + FileServiceImpl.nombreArchivo);
		serializer.write(empresa, result);
	}
}
