package com.novatronic.pscabas.gt.webcore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.novatronic.pscabas.gt.webcore.domains.esquema.DocAplicacion;
import com.novatronic.pscabas.gt.webcore.domains.responses.Response;
import com.novatronic.pscabas.gt.webcore.exception.MigradorException;
import com.novatronic.pscabas.gt.webcore.services.AplicacionMigradorService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("gt-webcore/api/v1/aplicacion")
public class AplicacionMigradorController {

	@Autowired
	private AplicacionMigradorService aplicacionMigradorService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "migrar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<DocAplicacion> migrarAplicacion(@RequestParam("version") String pVersion,
			@RequestParam("tipo") String pTipo) throws MigradorException {
		return new Response<>("Success", String.valueOf(HttpStatus.OK), "OK",
				aplicacionMigradorService.migrarAplicacion(pVersion, pTipo));
	}

}
