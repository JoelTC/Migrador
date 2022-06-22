package com.novatronic.pscabas.gt.webcore.services.implement;

import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.stereotype.Service;

import com.novatronic.pscabas.gt.webcore.business.EmpresaBusiness;
import com.novatronic.pscabas.gt.webcore.domains.esquema.DocEmpresa;
import com.novatronic.pscabas.gt.webcore.domains.esquema.RolPadre;
import com.novatronic.pscabas.gt.webcore.domains.request.AplicacionRequest;
import com.novatronic.pscabas.gt.webcore.domains.request.CifradoRequest;
import com.novatronic.pscabas.gt.webcore.domains.request.MigradorEmpresaRequest;
import com.novatronic.pscabas.gt.webcore.domains.request.RolPadreRequest;
import com.novatronic.pscabas.gt.webcore.domains.responses.AplicacionResponse;
import com.novatronic.pscabas.gt.webcore.exceptios.MigradorException;
import com.novatronic.pscabas.gt.webcore.services.interfaces.EmpresaMigradorService;

@Service
public class EmpresaMigradorServiceImpl implements EmpresaMigradorService {

	private Serializer serializer = new Persister();;
	private EmpresaBusiness business = new EmpresaBusiness();

	@Override
	public DocEmpresa migrarEmpresa(MigradorEmpresaRequest pMigradorEmpresa) throws MigradorException {

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
						pMigradorEmpresa.getFechaNacimiento(), pMigradorEmpresa.getRolPadre());
				break;

			default:
				oDocEmpresa = business.convertirVersion23(oDocEmpresa, pMigradorEmpresa.getTipo(),
						pMigradorEmpresa.getFechaNacimiento(), pMigradorEmpresa.getRolPadre());
			}

			return oDocEmpresa;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	@Override
	public List<AplicacionResponse> listarAplicacion() throws MigradorException {
		try {
			// Deserializa el archivo y lo conviete en el objeto aplicacion
			DocEmpresa oDocEmpresa = serializer.read(DocEmpresa.class, FileServiceImpl.archivo);

			return business.listarAplicacion(oDocEmpresa);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	@Override
	public DocEmpresa filtrarAplicacion(List<AplicacionRequest> pAplicacion) throws MigradorException {
		try {
			// Deserializa el archivo y lo conviete en el objeto aplicacion
			DocEmpresa oDocEmpresa = serializer.read(DocEmpresa.class, FileServiceImpl.archivo);
			return business.filtrarAplicacion(oDocEmpresa, pAplicacion);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	@Override
	public String listarCifrado() throws MigradorException {
		try {
			// Deserializa el archivo y lo conviete en el objeto aplicacion
			DocEmpresa oDocEmpresa = serializer.read(DocEmpresa.class, FileServiceImpl.archivo);

			return oDocEmpresa.getCifrado();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	@Override
	public String migrarCifrado(CifradoRequest pCifrado) throws MigradorException {
		try {
			// Deserializa el archivo y lo conviete en el objeto aplicacion
			DocEmpresa oDocEmpresa = serializer.read(DocEmpresa.class, FileServiceImpl.archivo);

			if (business.migrarCifrado(oDocEmpresa, pCifrado.getCifradoOrigen(), pCifrado.getCifradoDestino(),
					pCifrado.getContrasena()) != null) {
				return "Cambio de contraseña realizado";
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	@Override
	public List<RolPadre> listarRolPadre() throws MigradorException {
		try {
			// Deserializa el archivo y lo conviete en el objeto aplicacion
			DocEmpresa oDocEmpresa = serializer.read(DocEmpresa.class, FileServiceImpl.archivo);

			return oDocEmpresa.getlRolPadre();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	@Override
	public DocEmpresa renombrarRolPadre(List<RolPadreRequest> pRolPadre) throws MigradorException {
		try {
			// Deserializa el archivo y lo conviete en el objeto aplicacion
			DocEmpresa oDocEmpresa = serializer.read(DocEmpresa.class, FileServiceImpl.archivo);

			return business.renombrarRolPadre(oDocEmpresa, pRolPadre);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}
}