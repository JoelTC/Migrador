package com.novatronic.pscabas.gt.webcore.services.implement;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.stereotype.Service;

import com.novatronic.pscabas.gt.webcore.business.EmpresaBusiness;
import com.novatronic.pscabas.gt.webcore.domains.esquema.DocEmpresa;
import com.novatronic.pscabas.gt.webcore.domains.request.MigradorEmpresa;
import com.novatronic.pscabas.gt.webcore.exceptios.MigradorException;
import com.novatronic.pscabas.gt.webcore.services.interfaces.EmpresaMigradorService;

@Service
public class EmpresaMigradorServiceImpl implements EmpresaMigradorService {

	private Serializer serializer = new Persister();;
	private EmpresaBusiness business = new EmpresaBusiness();

	@Override
	public DocEmpresa migrarEmpresa(MigradorEmpresa pMigradorEmpresa) throws MigradorException {

		try {
			// Deserializa el archivo y lo conviete en el objeto empresa
			DocEmpresa oDocEmpresa = serializer.read(DocEmpresa.class, FileServiceImpl.archivo);

			switch (pMigradorEmpresa.getVersion()) {
			case "2.1":
				oDocEmpresa = business.convertirVersion21(oDocEmpresa, pMigradorEmpresa.getTipo(),
						pMigradorEmpresa.getFechaNacimiento(), pMigradorEmpresa.getRolPadre());
				break;

			case "2.3":
				oDocEmpresa = business.convertirVersion23(oDocEmpresa, pMigradorEmpresa.getTipo(),
						pMigradorEmpresa.getRolPadre());
				break;

			default:
				oDocEmpresa = business.convertirVersion23(oDocEmpresa, pMigradorEmpresa.getTipo(),
						pMigradorEmpresa.getRolPadre());
			}

			return oDocEmpresa;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	@Override
	public MigradorEmpresa listarAplicacion() throws MigradorException {
		try {
			MigradorEmpresa oMigradorEmpresa = new MigradorEmpresa();

			// Deserializa el archivo y lo conviete en el objeto aplicacion
			DocEmpresa oDocEmpresa = serializer.read(DocEmpresa.class, FileServiceImpl.archivo);

			oMigradorEmpresa.setlAplicacion(business.listarAplicacion(oDocEmpresa));

			return oMigradorEmpresa;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	@Override
	public MigradorEmpresa listarCifrado() throws MigradorException {
		try {
			MigradorEmpresa oMigradorEmpresa = new MigradorEmpresa();

			// Deserializa el archivo y lo conviete en el objeto aplicacion
			DocEmpresa oDocEmpresa = serializer.read(DocEmpresa.class, FileServiceImpl.archivo);

			oMigradorEmpresa.setCifrado(oDocEmpresa.getCifrado());

			return oMigradorEmpresa;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}
}