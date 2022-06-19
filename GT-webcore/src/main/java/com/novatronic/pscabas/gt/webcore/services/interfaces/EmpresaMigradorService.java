package com.novatronic.pscabas.gt.webcore.services.interfaces;

import com.novatronic.pscabas.gt.webcore.domains.esquema.DocEmpresa;
import com.novatronic.pscabas.gt.webcore.domains.request.MigradorEmpresa;
import com.novatronic.pscabas.gt.webcore.exceptios.MigradorException;

public interface EmpresaMigradorService {
	
	public DocEmpresa migrarEmpresa(MigradorEmpresa pMigradorEmpresa) throws MigradorException;

	public MigradorEmpresa listarAplicacion() throws MigradorException;
	
	public MigradorEmpresa listarCifrado() throws MigradorException;
}