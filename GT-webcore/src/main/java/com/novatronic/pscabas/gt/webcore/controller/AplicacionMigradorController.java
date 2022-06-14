package com.novatronic.pscabas.gt.webcore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.novatronic.pscabas.gt.webcore.domains.esquema.Empresa;
import com.novatronic.pscabas.gt.webcore.domains.responses.Response;
import com.novatronic.pscabas.gt.webcore.exceptios.MigradorException;
import com.novatronic.pscabas.gt.webcore.services.interfaces.AplicacionMigradorService;

@RestController
@RequestMapping("api/gt-webcore/v1/aplicacion")
public class AplicacionMigradorController {
	
	@Autowired
	private AplicacionMigradorService aplicacionMigradorService;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/migrar")
	public Response<Empresa> migrarAplicacion() throws MigradorException {
		return new Response<>("Success", String.valueOf(HttpStatus.OK), "OK", aplicacionMigradorService.convertirAplicacion());
	}

}
