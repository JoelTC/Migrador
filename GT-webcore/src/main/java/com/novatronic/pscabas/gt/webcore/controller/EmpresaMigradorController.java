package com.novatronic.pscabas.gt.webcore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.novatronic.pscabas.gt.webcore.domains.entities.AplicacionDTO;
import com.novatronic.pscabas.gt.webcore.domains.esquema.DocEmpresa;
import com.novatronic.pscabas.gt.webcore.domains.esquema.RolPadre;
import com.novatronic.pscabas.gt.webcore.domains.request.CifradoRequest;
import com.novatronic.pscabas.gt.webcore.domains.request.MigradorEmpresaRequest;
import com.novatronic.pscabas.gt.webcore.domains.request.RolPadreRequest;
import com.novatronic.pscabas.gt.webcore.domains.responses.Response;
import com.novatronic.pscabas.gt.webcore.exception.MigradorException;
import com.novatronic.pscabas.gt.webcore.services.EmpresaMigradorService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("gt-webcore/api/v1/empresa")
public class EmpresaMigradorController {

	@Autowired
	private EmpresaMigradorService empresaMigradorService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "migrar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<DocEmpresa> migrarEmpresa(@RequestBody MigradorEmpresaRequest pMigradorEmpresa)
			throws MigradorException {
		return new Response<>("Success", String.valueOf(HttpStatus.OK), "OK",
				empresaMigradorService.migrarEmpresa(pMigradorEmpresa));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "listarAplicacion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<List<AplicacionDTO>> listarAplicacion() throws MigradorException {
		return new Response<>("Success", String.valueOf(HttpStatus.OK), "OK",
				empresaMigradorService.listarAplicacion());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "filtrarAplicacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<DocEmpresa> filtrarAplicacion(@RequestBody List<AplicacionDTO> pAplicacion)
			throws MigradorException {
		return new Response<>("Success", String.valueOf(HttpStatus.OK), "OK",
				empresaMigradorService.filtrarAplicacion(pAplicacion));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "listarCifrado", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> listarCifrado() throws MigradorException {
		return new Response<>("Success", String.valueOf(HttpStatus.OK), "OK", empresaMigradorService.listarCifrado());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "migrarCifrado", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> migrarCifrado(@RequestBody CifradoRequest pCifrado) throws MigradorException {
		return new Response<>("Success", String.valueOf(HttpStatus.OK), "OK",
				empresaMigradorService.migrarCifrado(pCifrado));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "listarRolPadre", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<List<RolPadre>> listarRolPadre() throws MigradorException {
		return new Response<>("Success", String.valueOf(HttpStatus.OK), "OK", empresaMigradorService.listarRolPadre());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "renombrarRolPadre", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<DocEmpresa> renombrarRolPadre(@RequestBody List<RolPadreRequest> pRolPadre)
			throws MigradorException {
		return new Response<>("Success", String.valueOf(HttpStatus.OK), "OK",
				empresaMigradorService.renombrarRolPadre(pRolPadre));
	}

}
