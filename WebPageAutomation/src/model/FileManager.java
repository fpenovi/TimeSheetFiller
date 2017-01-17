package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import model.exceptions.FailedToCreateNewFileException;
import model.exceptions.FailedToDeleteFileException;

public class FileManager {
	
	public static void createFile(String path) {
		
		// CREO EL ARCHIVO Y TODOS SUS DIRECTORIOS PADRES NECESARIOS
		File f = new File(path);
		f.getParentFile().mkdirs();
		
		try {
			f.createNewFile();
		} catch (IOException e1) {			
			throw new FailedToCreateNewFileException(e1);
		}
	}
	
	public static void deleteFile(String path, String filename) {		
		Path deletePath = FileSystems.getDefault().getPath(path, filename);
		
		try {
			Files.deleteIfExists(deletePath);
				
		} catch (IOException e) {
				throw new FailedToDeleteFileException(e);
		}
	}

}
