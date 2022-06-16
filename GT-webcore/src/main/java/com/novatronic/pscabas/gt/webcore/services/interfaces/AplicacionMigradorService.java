package com.novatronic.pscabas.gt.webcore.services.interfaces;

import com.novatronic.pscabas.gt.webcore.domains.esquema.DocAplicacion;

public interface AplicacionMigradorService {
	
	public DocAplicacion migrarAplicacion(String version, String tipo);

}
