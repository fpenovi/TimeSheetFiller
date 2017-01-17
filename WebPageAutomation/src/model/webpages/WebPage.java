package model.webpages;

import java.util.Arrays;
import java.util.List;

import model.Automatable;
import model.exceptions.CodeDoesNotExistException;
import model.webpages.timesheet.TimeSheetAccesser;

public enum WebPage {
	
	TIMESHEET("TS", new TimeSheetAccesser(), "TimeSheet"),
	FACEBOOK("FB", new TimeSheetAccesser(), "Facebook"),
	BUBBLE("BU", new TimeSheetAccesser(), "Bubble");
	
	private String code;
	private Automatable webpage;
	private String description;
	
	private WebPage(String code, Automatable webpage, String description) {
		this.code = code;
		this.webpage = webpage;
		this.description = description;
	}
	
	public static Automatable getAutomatableByCode(String code) {
		for (WebPage page : WebPage.values()) {
			if (page.code.equals(code))
				return page.webpage;
		}
		
		throw new CodeDoesNotExistException("Code: " + code);
	}
	
	public static List<WebPage> getListOfWebPages() {
		return Arrays.asList(WebPage.values());
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Automatable getWebPage() {
		return this.webpage;
	}
	
	@Override
	public String toString() {
		return this.description;
	}

}
