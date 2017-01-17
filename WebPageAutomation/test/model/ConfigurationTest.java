package model;

import org.junit.Assert;
import org.junit.Test;


public class ConfigurationTest {

	@Test
	public void configurationDirectoryDoesntHaveDefaultUserFile() {
		
		Configuration config = new Configuration();
		config.setProductName("TimeSheet");
		User user = new User(config);
		
		// Borro el archivo que crea el constructor de user
		FileManager.deleteFile(config.getConfigDirectory(), "no-name" + config.getFileExtension());
		
		Assert.assertEquals(false, user.hasStoredConfig());
	}
	
	
	@Test
	public void configurationDirectoryHasDefaultUserFile() {
		
		String userName = "no-name";				
		Configuration config = new Configuration();
		config.setProductName("TimeSheet");
		String dir = config.getConfigDirectory() + userName + config.getFileExtension();
		
		// CREO EL ARCHIVO PARA SIMULAR QUE TIENE UNA CONFIG GUARDADA
		FileManager.createFile(dir);
		
		User user = new User(config);
		Boolean result = user.hasStoredConfig();
					
		FileManager.deleteFile(config.getConfigDirectory(), "no-name" + config.getFileExtension());
		
		Assert.assertEquals(true, result);
	}
		
}

