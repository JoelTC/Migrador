package com.novatronic.pscabas.gt.webcore.services.interfaces;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.novatronic.pscabas.gt.webcore.exceptios.MigradorException;

public interface FileService {

	public String save(MultipartFile file, String path) throws MigradorException;

	public Resource load(String name) throws MigradorException;

	public Stream<Path> loadAll() throws Exception;
}
