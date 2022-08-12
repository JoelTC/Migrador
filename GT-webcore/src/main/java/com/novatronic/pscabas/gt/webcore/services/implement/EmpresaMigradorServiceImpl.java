package com.novatronic.pscabas.gt.webcore.services.implement;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novatronic.pscabas.gt.webcore.business.EmpresaBusiness;
import com.novatronic.pscabas.gt.webcore.domains.entities.AplicacionDTO;
import com.novatronic.pscabas.gt.webcore.domains.entities.AplicacionUsuario;
import com.novatronic.pscabas.gt.webcore.domains.esquema.DocEmpresa;
import com.novatronic.pscabas.gt.webcore.domains.esquema.RolPadre;
import com.novatronic.pscabas.gt.webcore.domains.request.CifradoRequest;
import com.novatronic.pscabas.gt.webcore.domains.request.MigradorEmpresaRequest;
import com.novatronic.pscabas.gt.webcore.domains.request.RolPadreRequest;
import com.novatronic.pscabas.gt.webcore.exception.MigradorException;
import com.novatronic.pscabas.gt.webcore.repositories.AplicacionUsuarioRepository;
import com.novatronic.pscabas.gt.webcore.services.EmpresaMigradorService;

@Service
public class EmpresaMigradorServiceImpl implements EmpresaMigradorService {
	protected static final Logger logger = LogManager.getLogger(EmpresaMigradorServiceImpl.class);

	@Autowired
	private AplicacionUsuarioRepository repository;

	private Serializer serializer = new Persister();;
	private EmpresaBusiness business = new EmpresaBusiness();

	@Override
	public DocEmpresa migrarEmpresa(MigradorEmpresaRequest pMigradorEmpresa) throws MigradorException {

		try {
			// Deserializa el archivo y lo conviete en el objeto empresa
			DocEmpresa oDocEmpresa = serializer.read(DocEmpresa.class, FileServiceImpl.archivo);

			List<AplicacionUsuario> lAplicacionUsuario = listarAplicacionUsuario(oDocEmpresa.getMnemonico());

			switch (pMigradorEmpresa.getVersion()) {
			case "2.1":
				oDocEmpresa = business.convertirVersion21(oDocEmpresa, pMigradorEmpresa, lAplicacionUsuario);
				break;

			case "2.3":
				oDocEmpresa = business.convertirVersion23(oDocEmpresa, pMigradorEmpresa, lAplicacionUsuario);
				break;

			default:
				oDocEmpresa = business.convertirVersion23(oDocEmpresa, pMigradorEmpresa, lAplicacionUsuario);
			}

			business.gestionarArchivos(oDocEmpresa);
			return oDocEmpresa;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public List<AplicacionDTO> listarAplicacion() throws MigradorException {
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
	public DocEmpresa filtrarAplicacion(List<AplicacionDTO> pAplicacion) throws MigradorException {
		try {
			// Deserializa el archivo y lo conviete en el objeto aplicacion
			DocEmpresa oDocEmpresa = serializer.read(DocEmpresa.class, FileServiceImpl.archivo);

			oDocEmpresa = business.filtrarAplicacion(oDocEmpresa, pAplicacion);

			business.gestionarArchivos(oDocEmpresa);

			return oDocEmpresa;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			logger.error(e.getMessage(), e);
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
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public String migrarCifrado(CifradoRequest pCifrado) throws MigradorException {
		try {
			// Deserializa el archivo y lo conviete en el objeto aplicacion
			DocEmpresa oDocEmpresa = serializer.read(DocEmpresa.class, FileServiceImpl.archivo);

			oDocEmpresa = business.migrarCifrado(oDocEmpresa, pCifrado.getCifradoOrigen(), pCifrado.getCifradoDestino(),
					pCifrado.getContrasena());
			if (oDocEmpresa != null) {
				business.gestionarArchivos(oDocEmpresa);
				return "Cambio de contraseña realizado";

			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			logger.error(e.getMessage(), e);
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
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public DocEmpresa renombrarRolPadre(List<RolPadreRequest> pRolPadre) throws MigradorException {
		try {
			// Deserializa el archivo y lo conviete en el objeto aplicacion
			DocEmpresa oDocEmpresa = serializer.read(DocEmpresa.class, FileServiceImpl.archivo);
			oDocEmpresa = business.renombrarRolPadre(oDocEmpresa, pRolPadre);

			business.gestionarArchivos(oDocEmpresa);

			return oDocEmpresa;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	private List<AplicacionUsuario> listarAplicacionUsuario(String empresa) throws MigradorException {
		try {
			return this.repository.listarAplicacionUsuario(ConexionBDServiceImpl.conexionBD, empresa);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			logger.error(e.getMessage(), e);
			return null;
		}
	}
}