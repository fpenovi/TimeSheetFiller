package model;

import java.io.File;

public class Configuration {
	
	private String configDirectory;
	private String fileExtension;
	private Boolean isProductSet;
	
	
	public Configuration() {
		String sessionUser = System.getProperty("user.name");
		this.configDirectory = "C:/Users/" + sessionUser + "/AppData/Local/WebPageAutomation/";
		this.fileExtension = ".properties";
		this.isProductSet = false;
	}
	
	
	public Boolean configFileExists(String fileName) {
		return new File(configDirectory + fileName + fileExtension).isFile();
	}
	
	
	public void loadConfigFromFile(String userName) {
		// TODO implement
	}
	
	
	public void createUserData(String userName) {
		FileManager.createFile(configDirectory + userName + fileExtension);
	}


	public String getFileExtension() {
		return this.fileExtension;
	}
	
	
	public String getConfigDirectory() {
		return this.configDirectory;
	} 
	

	public void setProductName(String productName) {
		if (isProductSet)
			return;
		
		this.configDirectory += productName + "/";
		this.isProductSet = true;
	}

}
