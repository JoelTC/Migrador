package com.novatronic.pscabas.gt.webcore.services.interfaces;

import com.novatronic.pscabas.gt.webcore.domains.esquema.DocEmpresa;
import com.novatronic.pscabas.gt.webcore.domains.request.MigradorEmpresa;

public interface EmpresaMigradorService {
	//String version, String rolPadre, String tipo
	public DocEmpresa migrarEmpresa(MigradorEmpresa mEmpresa);
	
	public MigradorEmpresa listarAplicacion();

}
