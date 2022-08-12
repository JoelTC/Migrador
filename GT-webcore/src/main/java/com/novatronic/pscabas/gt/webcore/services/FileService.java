package com.novatronic.pscabas.gt.webcore.services;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.novatronic.pscabas.gt.webcore.exception.MigradorException;

public interface FileService {

	public String save(MultipartFile pFile) throws MigradorException;

	public Resource load(String pFilename) throws MigradorException;

	public Stream<Path> loadAll() throws Exception;
}