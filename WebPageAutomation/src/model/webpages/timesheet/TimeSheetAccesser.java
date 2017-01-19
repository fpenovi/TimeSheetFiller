package model.webpages.timesheet;

import java.io.IOException;

import model.Automatable;
import model.User;
import model.exceptions.CannotGetPageFromWebClientException;
import model.exceptions.DomElementNotFoundException;
import model.exceptions.FailedToSubmitFormException;
import model.exceptions.IncorrectUsernameOrPasswordException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class TimeSheetAccesser implements Automatable {
	
	private String startPointUrl = "https://webi.certant.com/timesheet";
	private WebClient webClient;
	private HtmlPage currentPage;
	private User user;
	
	
	public TimeSheetAccesser() {}
	
	
	@Override
	public Automatable create() {
		
		this.webClient = new WebClient();
		this.webClient.getOptions().setUseInsecureSSL(true);
		
		try {
			this.currentPage = this.webClient.getPage(startPointUrl);
		} catch (Exception e) {
			throw new CannotGetPageFromWebClientException(e);
		}
		
		return this;
	}
	
	
	@Override
	public void doLogin() {
		
		HtmlTextInput inputUserName = currentPage.querySelector("input[name=username]");
		HtmlPasswordInput inputPassword = currentPage.querySelector("input[name=password]");
		HtmlSubmitInput submitButton = currentPage.querySelector("input[name=Login]");
		
		if (inputUserName == null || inputPassword == null || submitButton == null)
			throw new DomElementNotFoundException();
		
		inputUserName.setText(user.getUserName());
		inputPassword.setText(user.getPassword());
		
		try {
			this.currentPage = submitButton.click();
		} catch (IOException e) {
			throw new FailedToSubmitFormException();
		}
		
		DomNode error = currentPage.querySelector(".login_error");
		
		if (error != null)
			throw new IncorrectUsernameOrPasswordException();
	}
	
	
	@Override
	public void close() {
		if (this.webClient == null)
			return;
		
		this.webClient.close();
	}

	
	@Override
	public String getCurrentPage() {
		return this.currentPage.getUrl().toString();
	}
	
	
	@Override
	public void setUser(User user) {
		this.user = user;
		this.user.getUserConfig().setProductName("TimeSheet");
		
		if (user.hasStoredConfig())
			user.loadUserData();
		
		else
			user.createUserData();
	}

}
