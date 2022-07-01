package com.novatronic.pscabas.gt.webcore.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import com.novatronic.pscabas.gt.webcore.domains.responses.Response;
import com.novatronic.pscabas.gt.webcore.exceptios.MigradorException;
import com.novatronic.pscabas.gt.webcore.services.interfaces.FileService;

public class FileControllerTest {
	private static final String PATH = "C:\\Users\\jtrujillo\\Desktop\\templates\\templates";
	private MockMultipartFile avatar = null;

	private static final String SUCCESS_STATUS = "Success";
	private static final String SUCCESS_CODE = "200 OK";
	private static final String SUCCESS_MESSAGE = "OK";
	private static final String ESTREG = "Los archivos fueron cargados correctamente al servidor";

	@Mock
	FileService service;

	@InjectMocks
	FileController controller;

	@Before
	public void init() throws MigradorException, IOException {
		MockitoAnnotations.initMocks(this);

		final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.png");
		avatar = new MockMultipartFile("file", "test.png", "image/png", inputStream);

		Mockito.when(service.save(avatar)).thenReturn(ESTREG);
	}

	@Test
	public void uploadFiles() throws MigradorException {
		final Response<String> response = controller.uploadFiles(avatar);
		
		assertEquals(response.getStatuts(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);
		assertEquals(response.getMessage(), SUCCESS_MESSAGE);
		assertEquals(response.getData(), ESTREG);
	}
}
