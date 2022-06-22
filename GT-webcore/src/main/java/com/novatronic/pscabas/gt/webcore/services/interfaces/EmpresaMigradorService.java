package com.novatronic.pscabas.gt.webcore.services.interfaces;

import java.util.List;

import com.novatronic.pscabas.gt.webcore.domains.esquema.DocEmpresa;
import com.novatronic.pscabas.gt.webcore.domains.esquema.RolPadre;
import com.novatronic.pscabas.gt.webcore.domains.request.AplicacionRequest;
import com.novatronic.pscabas.gt.webcore.domains.request.CifradoRequest;
import com.novatronic.pscabas.gt.webcore.domains.request.MigradorEmpresaRequest;
import com.novatronic.pscabas.gt.webcore.domains.request.RolPadreRequest;
import com.novatronic.pscabas.gt.webcore.domains.responses.AplicacionResponse;
import com.novatronic.pscabas.gt.webcore.exceptios.MigradorException;

public interface EmpresaMigradorService {

	public DocEmpresa migrarEmpresa(MigradorEmpresaRequest pMigradorEmpresa) throws MigradorException;

	public List<AplicacionResponse> listarAplicacion() throws MigradorException;

	public DocEmpresa filtrarAplicacion(List<AplicacionRequest> pAplicacion) throws MigradorException;

	public String listarCifrado() throws MigradorException;

	public String migrarCifrado(CifradoRequest pCifrado) throws MigradorException;

	public List<RolPadre> listarRolPadre() throws MigradorException;

	public DocEmpresa renombrarRolPadre(List<RolPadreRequest> pRolPadre) throws MigradorException;
}