package com.novatronic.pscabas.gt.webcore.services.implement;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.novatronic.pscabas.gt.webcore.exceptios.MigradorException;
import com.novatronic.pscabas.gt.webcore.services.interfaces.FileService;
import com.novatronic.pscabas.gt.webcore.util.Constantes;

@Service
public class FileServiceImpl implements FileService {

	private final Path rootFolder = Paths.get("uploads");

	public static String nombreArchivo; // Variable estatica para el nombre del archivo
	
	@Value("${ruta}")
	private String ruta;
	
	public static Path rutaFolder; // Variable estatica para la ruta en donde se alojara el archivo
	public static File archivo; // Variable que contendra al documento xml

	private String manejoNombre(String pFile, int i) {
		// Se renombra el archivo con la fecha de carga
		
		String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constantes.DATE_FORMAT));

		if (i == 0) {
			nombreArchivo = pFile.substring(0, pFile.length() - 4) + "-copia-" + fecha + ".xml";
			return nombreArchivo;
		} else {
			nombreArchivo = pFile.substring(0, pFile.length() - 4) + "-copia-" + fecha + " (" + i + ").xml";
			return nombreArchivo;
		}
	}

	@Override
	public String save(MultipartFile pFile) throws MigradorException {
		try {
			rutaFolder = Paths.get(ruta);
			nombreArchivo = manejoNombre(pFile.getOriginalFilename(), 0);
			archivo = new File(rutaFolder.toAbsolutePath() + "\\" + nombreArchivo);

			if (!archivo.exists()) {
				Files.copy(pFile.getInputStream(), rutaFolder.resolve(nombreArchivo));
				return "Los archivos fueron cargados correctamente al servidor";
			} else {
				boolean f = true;
				int i = 1;
				while (f) {
					nombreArchivo = manejoNombre(pFile.getOriginalFilename(), i);
					archivo = new File(rutaFolder.toAbsolutePath() + "\\" + nombreArchivo);

					if (!archivo.exists()) {
						Files.copy(pFile.getInputStream(), rutaFolder.resolve(nombreArchivo));
						f = false;
						return "Los archivos fueron cargados correctamente al servidor";
					}
					i++;
				}
			}

			return null;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	@Override
	public Resource load(String pFilename) throws MigradorException {
		try {
			archivo = new File(rutaFolder + "\\" + pFilename);
			if (archivo.exists()) {
				Path file = rutaFolder.resolve(pFilename);
				Resource resource = new UrlResource(file.toUri());
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}

	@Override
	public Stream<Path> loadAll() throws Exception {
		return Files.walk(rootFolder, 1).filter(path -> !path.equals(rootFolder)).map(rootFolder::relativize);
	}

}
