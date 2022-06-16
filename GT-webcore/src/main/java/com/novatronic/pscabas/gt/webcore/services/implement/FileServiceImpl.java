package com.novatronic.pscabas.gt.webcore.services.implement;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.novatronic.pscabas.gt.webcore.exceptios.MigradorException;
import com.novatronic.pscabas.gt.webcore.services.interfaces.FileService;

@Service
public class FileServiceImpl implements FileService {

	private final Path rootFolder = Paths.get("uploads");

	public static String nombreArchivo; // Variable estatica para el nombre del archivo
	public static Path rutaFolder; // Variable estatica para la ruta en donde se alojara el archivo
	public static File archivo; // Variable que contendra al documento xml

	private String manejoNombre(String file, int i) {
		// Se renombra el archivo con la fecha de carga
		String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yy"));

		if (i == 0) {
			nombreArchivo = file.substring(0, file.length() - 4) + "-copia-" + fecha + ".xml";
			return nombreArchivo;
		} else {
			nombreArchivo = file.substring(0, file.length() - 4) + "-copia-" + fecha + " (" + i + ").xml";
			return nombreArchivo;
		}
	}

	@Override
	public String save(MultipartFile file, String path) throws MigradorException {
		try {
			rutaFolder = Paths.get(path);
			nombreArchivo = manejoNombre(file.getOriginalFilename(), 0);
			archivo = new File(rutaFolder.toAbsolutePath() + "\\" + nombreArchivo);

			if (!archivo.exists()) {
				Files.copy(file.getInputStream(), rutaFolder.resolve(nombreArchivo));
				return "Los archivos fueron cargados correctamente al servidor";
			} else {
				boolean f = true;
				int i = 1;
				while (f) {
					nombreArchivo = manejoNombre(file.getOriginalFilename(), i);
					archivo = new File(rutaFolder.toAbsolutePath() + "\\" + nombreArchivo);

					if (!archivo.exists()) {
						Files.copy(file.getInputStream(), rutaFolder.resolve(nombreArchivo));
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
	public Resource load(String name) throws MigradorException {
		try {
			archivo = new File(rutaFolder + "\\" + name);
			if (archivo.exists()) {
				Path file = rutaFolder.resolve(name);
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
