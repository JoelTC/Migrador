package com.novatronic.pscabas.gt.webcore.services.implement;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.springframework.stereotype.Service;

import com.novatronic.pscabas.gt.webcore.domains.esquema.Empresa;
import com.novatronic.pscabas.gt.webcore.services.interfaces.EmpresaMigradorService;

@Service
public class EmpresaMidradorServiceImpl implements EmpresaMigradorService {

	@Override
	public Empresa convertirEmpresa() {
		try {
			Format formato = new Format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); //Formato para el documento xml
			
			Serializer serializer = new Persister(formato); //Librería para serializar y deserializar los archivos xml
			
			Empresa empresa = serializer.read(Empresa.class, FileServiceImpl.archivo); //Deserializa el archivo y lo conviete en el objeto empresa
			
			File result = new File(FileServiceImpl.rutaFolder + "\\example.xml");

			serializer.write(empresa, result);
			
			
			return empresa;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}
}
