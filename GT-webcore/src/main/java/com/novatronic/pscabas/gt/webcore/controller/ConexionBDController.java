package com.novatronic.pscabas.gt.webcore.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.novatronic.pscabas.gt.webcore.domains.entities.ConexionBD;
import com.novatronic.pscabas.gt.webcore.domains.responses.Response;
import com.novatronic.pscabas.gt.webcore.services.interfaces.ConexionBDService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("gt-webcore/api/v1/bd")
public class ConexionBDController {
	@Autowired
	private ConexionBDService bdService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "conexion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Boolean> conexionBD(@RequestBody ConexionBD conexion) throws SQLException {
		return new Response<>("Success", String.valueOf(HttpStatus.OK), "OK", bdService.conexionBD(conexion));
	}
}
