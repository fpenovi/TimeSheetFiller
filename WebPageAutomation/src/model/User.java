package model;

public class User {
	
	private String userName;
	private String password;
	private Configuration userConfig;
	
	public User(Configuration config) {
		this.userConfig = config;
		this.userName = "no-name";
		this.password = "no-password";			
	}
	
	
	public Boolean hasStoredConfig() {
		return this.userConfig.configFileExists(this.userName);
	}
	
	
	public void loadUserData() {
		this.userConfig.loadConfigFromFile(this.userName);
	}
	
	
	public void createUserData() {
		this.userConfig.createUserData(this.userName);
	}
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Configuration getUserConfig() {
		return userConfig;
	}

	public void setUserConfig(Configuration userConfig) {
		this.userConfig = userConfig;
	}
	
	
}
