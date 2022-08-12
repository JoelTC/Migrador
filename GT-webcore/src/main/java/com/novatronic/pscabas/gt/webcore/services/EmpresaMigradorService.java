package com.novatronic.pscabas.gt.webcore.services;

import java.util.List;

import com.novatronic.pscabas.gt.webcore.domains.entities.AplicacionDTO;
import com.novatronic.pscabas.gt.webcore.domains.esquema.DocEmpresa;
import com.novatronic.pscabas.gt.webcore.domains.esquema.RolPadre;
import com.novatronic.pscabas.gt.webcore.domains.request.CifradoRequest;
import com.novatronic.pscabas.gt.webcore.domains.request.MigradorEmpresaRequest;
import com.novatronic.pscabas.gt.webcore.domains.request.RolPadreRequest;
import com.novatronic.pscabas.gt.webcore.exception.MigradorException;

public interface EmpresaMigradorService {

	public DocEmpresa migrarEmpresa(MigradorEmpresaRequest pMigradorEmpresa) throws MigradorException;

	public List<AplicacionDTO> listarAplicacion() throws MigradorException;

	public DocEmpresa filtrarAplicacion(List<AplicacionDTO> pAplicacion) throws MigradorException;

	public String listarCifrado() throws MigradorException;

	public String migrarCifrado(CifradoRequest pCifrado) throws MigradorException;

	public List<RolPadre> listarRolPadre() throws MigradorException;

	public DocEmpresa renombrarRolPadre(List<RolPadreRequest> pRolPadre) throws MigradorException;
}