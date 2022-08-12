package com.novatronic.pscabas.gt.webcore.services.implement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.stereotype.Service;

import com.novatronic.pscabas.gt.webcore.business.AplicacionBusiness;
import com.novatronic.pscabas.gt.webcore.domains.esquema.DocAplicacion;
import com.novatronic.pscabas.gt.webcore.exception.MigradorException;
import com.novatronic.pscabas.gt.webcore.services.AplicacionMigradorService;

@Service
public class AplicacionMigradorServiceImpl implements AplicacionMigradorService {
	protected static final Logger logger = LogManager.getLogger(AplicacionMigradorServiceImpl.class);

	private AplicacionBusiness business = new AplicacionBusiness();
	private Serializer serializer = new Persister();;

	@Override
	public DocAplicacion migrarAplicacion(String pVersion, String pTipo) throws MigradorException {
		try {

			// Deserializa el archivo y lo conviete en el objeto aplicacion
			DocAplicacion oDocAplicacion = serializer.read(DocAplicacion.class, FileServiceImpl.archivo);

			switch (pVersion) {
			case "2.1":
				oDocAplicacion = business.convertirVersion21(oDocAplicacion, pTipo);
				break;

			case "2.3":
				oDocAplicacion = business.convertirVersion23(oDocAplicacion, pTipo);
				break;

			default:
				oDocAplicacion = business.convertirVersion23(oDocAplicacion, pTipo);
			}

			return oDocAplicacion;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			logger.error(e.getMessage(), e);
			return null;
		}
	}
}