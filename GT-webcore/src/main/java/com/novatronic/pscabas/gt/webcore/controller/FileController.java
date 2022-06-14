package com.novatronic.pscabas.gt.webcore.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.novatronic.pscabas.gt.webcore.domains.entities.File;
import com.novatronic.pscabas.gt.webcore.domains.responses.Response;
import com.novatronic.pscabas.gt.webcore.exceptios.MigradorException;
import com.novatronic.pscabas.gt.webcore.services.interfaces.FileService;

@RestController
@RequestMapping("api/gt-webcore/v1/file")
public class FileController {

	@Autowired
	private FileService fileService;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/upload")
	public Response<String> uploadFiles(@RequestParam("files") MultipartFile files, @RequestParam("path") String path) throws MigradorException {
		return new Response<>("Success", String.valueOf(HttpStatus.OK), "OK", fileService.save(files, path));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) throws MigradorException {
		Resource resource = fileService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/all")
	public Response<List<File>> getAllFiles() throws Exception {
		List<File> files = fileService.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(FileController.class, "getFile", path.getFileName().toString()).build().toString();
			return new File(filename, url);
		}).collect(Collectors.toList());

		return new Response<>("Success", String.valueOf(HttpStatus.OK), "OK", files);
	}
}
