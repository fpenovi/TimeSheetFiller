package webpages;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Configuration;
import model.FileManager;
import model.User;
import model.exceptions.IncorrectUsernameOrPasswordException;
import model.webpages.timesheet.TimeSheetAccesser;


public class TimeSheetAccesserTest {
	
	private TimeSheetAccesser webPageAccesser;
	private Configuration config;
	private User user;
	private String resourceFolder = File.separator + "resources";
	
	@Before
	public void createWebPageAcceser() {
		this.webPageAccesser = (TimeSheetAccesser) new TimeSheetAccesser().create();
	}
	
	@After
	public void tearDownResources() {
		this.webPageAccesser.close();
		FileManager.deleteFile(config.getConfigDirectory(), user.getUserName() + config.getFileExtension());
	}
	

	@Test (expected = IncorrectUsernameOrPasswordException.class)
	public void loginWithIncorrectUsernameOrPasswordThrowsException() {		
		
		config = new Configuration();
		user = new User(config);
		webPageAccesser.setUser(user);
				
		webPageAccesser.doLogin();
	}
	
	
	@Test
	public void loginWithCorrectUsernameAndPasswordForwardsToNextPage() {
		
		config = new Configuration();
		user = new User(config);
		UserMock validUser = new UserMock();
		user.setUserName(validUser.getUserName());
		user.setPassword(validUser.getPassword());
		webPageAccesser.setUser(user);
				
		webPageAccesser.doLogin();
		
		String urlNextPage = webPageAccesser.getCurrentPage();		
		Assert.assertEquals("https://webi.certant.com/timesheet/calendar.php", urlNextPage);
	}
	
	
	private class UserMock {
		
		String userName;
		String password;
		String fileName = "valid-user.properties";
		final String seed = "mock-valid-user";
		
		UserMock() {
			String filePath = new File("").getAbsolutePath() + resourceFolder + File.separator + fileName;
			
			Properties prop = new Properties();
			InputStream input = null;
			
			try {
				input = new FileInputStream(filePath);
				prop.load(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
			encryptor.setPassword(seed);

			this.userName = encryptor.decrypt(prop.getProperty("username"));
			this.password = encryptor.decrypt(prop.getProperty("password"));
		}
		
		
		String getUserName() {
			return this.userName;			
		}
		
		String getPassword() {
			return this.password;
		}
	}

}
