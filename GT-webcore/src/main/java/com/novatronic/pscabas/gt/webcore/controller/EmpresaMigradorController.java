package com.novatronic.pscabas.gt.webcore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.novatronic.pscabas.gt.webcore.domains.esquema.DocEmpresa;
import com.novatronic.pscabas.gt.webcore.domains.request.MigradorEmpresa;
import com.novatronic.pscabas.gt.webcore.domains.responses.Response;
import com.novatronic.pscabas.gt.webcore.exceptios.MigradorException;
import com.novatronic.pscabas.gt.webcore.services.interfaces.EmpresaMigradorService;

@RestController
@RequestMapping("api/gt-webcore/v1/empresa")
public class EmpresaMigradorController {

	@Autowired
	private EmpresaMigradorService empresaMigradorService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "migrar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<DocEmpresa> migrarEmpresa(@RequestBody MigradorEmpresa pMigradorEmpresa) throws MigradorException {
		return new Response<>("Success", String.valueOf(HttpStatus.OK), "OK",
				empresaMigradorService.migrarEmpresa(pMigradorEmpresa));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "listarAplicacion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<MigradorEmpresa> listarAplicacion() throws MigradorException {
		return new Response<>("Success", String.valueOf(HttpStatus.OK), "OK",
				empresaMigradorService.listarAplicacion());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "listarCifrado", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<MigradorEmpresa> listarCifrado() throws MigradorException {
		return new Response<>("Success", String.valueOf(HttpStatus.OK), "OK", empresaMigradorService.listarCifrado());
	}

}
