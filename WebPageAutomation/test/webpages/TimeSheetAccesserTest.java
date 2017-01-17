package webpages;
import model.Configuration;
import model.FileManager;
import model.User;
import model.exceptions.IncorrectUsernameOrPasswordException;
import model.webpages.timesheet.TimeSheetAccesser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class TimeSheetAccesserTest {
	
	private TimeSheetAccesser webPageAccesser;
	private Configuration config;
	private User user;
	
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
		MockUser validUser = new MockUser();
		user.setUserName(validUser.getUserName());
		user.setPassword(validUser.getPassword());
		webPageAccesser.setUser(user);
				
		webPageAccesser.doLogin();
		
		String urlNextPage = webPageAccesser.getCurrentPage();		
		Assert.assertEquals("https://webi.certant.com/timesheet/calendar.php", urlNextPage);
	}

}
