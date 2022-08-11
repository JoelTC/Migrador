package com.novatronic.pscabas.gt.webcore.services;

import com.novatronic.pscabas.gt.webcore.domains.esquema.DocAplicacion;
import com.novatronic.pscabas.gt.webcore.exceptios.MigradorException;

public interface AplicacionMigradorService {
	
	public DocAplicacion migrarAplicacion(String pVersion, String pTipo) throws MigradorException;
}