package com.novatronic.pscabas.gt.webcore.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import com.novatronic.pscabas.gt.webcore.domains.responses.Response;
import com.novatronic.pscabas.gt.webcore.exception.MigradorException;
import com.novatronic.pscabas.gt.webcore.services.FileService;

public class FileControllerTest {
	MockMultipartFile multipartFile = new MockMultipartFile("pFiles", "test.png", "image/png", "Some bytes".getBytes());

	private static final String SUCCESS_STATUS = "Success";
	private static final String SUCCESS_CODE = "200 OK";
	private static final String SUCCESS_MESSAGE = "OK";
	private static final String ESTREG = "Los archivos fueron cargados correctamente al servidor";
	private static final String RUTA = "C:\\Users\\jtrujillo\\Desktop\\templates";
	@Mock
	FileService service;

	@InjectMocks
	FileController controller;

	@Before
	public void init() throws MigradorException, IOException {
		MockitoAnnotations.initMocks(this);
		Mockito.when(service.save(multipartFile)).thenReturn(ESTREG);
	}

	@Test
	public void uploadFiles() throws MigradorException {
		final Response<String> response = controller.uploadFiles(multipartFile);

		assertEquals(response.getStatuts(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);
		assertEquals(response.getMessage(), SUCCESS_MESSAGE);
		assertEquals(response.getData(), ESTREG);
	}
}
