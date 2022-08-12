package com.novatronic.pscabas.gt.webcore.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.novatronic.pscabas.gt.webcore.domains.responses.Response;
import com.novatronic.pscabas.gt.webcore.exception.MigradorException;
import com.novatronic.pscabas.gt.webcore.services.EmpresaMigradorService;

public class EmpresaMigradorControlleTest {

	private static final String CIFRADO = "1";

	private static final String SUCCESS_STATUS = "Success";
	private static final String SUCCESS_CODE = "200 OK";
	private static final String SUCCESS_MESSAGE = "OK";

	@Mock
	EmpresaMigradorService service;

	@InjectMocks
	EmpresaMigradorController controller;

	@Before
	public void init() throws MigradorException {
		MockitoAnnotations.initMocks(this);
		Mockito.when(service.listarCifrado()).thenReturn(CIFRADO);
	}
	
	@Test
	public void listarCifrado() throws MigradorException {
		final Response<String> response = controller.listarCifrado();

		assertEquals(response.getStatuts(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);
		assertEquals(response.getMessage(), SUCCESS_MESSAGE);
		assertEquals(response.getData(), CIFRADO);
	}
}
