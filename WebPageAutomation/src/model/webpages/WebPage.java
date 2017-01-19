package model.webpages;

import java.util.Arrays;
import java.util.List;

import model.Automatable;
import model.exceptions.CodeDoesNotExistException;
import model.webpages.timesheet.TimeSheetAccesser;

public enum WebPage {
	
	TIMESHEET("TS", new TimeSheetAccesser(), "TimeSheet", "Automated and customizable app for easy schedule loading."),
	HOTMAIL("FB", new TimeSheetAccesser(), "Hotmail", "Send mails from your Hotmail account with ease from the comfort of your desktop."),
	GMAIL("BU", new TimeSheetAccesser(), "Gmail", "Send mails from your Gmail account with ease from the comfort of your desktop.");
	
	private String code;
	private Automatable webpage;
	private String name;
	private String description;
	
	private WebPage(String code, Automatable webpage, String name, String description) {
		this.code = code;
		this.webpage = webpage;
		this.name = name;
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
	
	public String getCode() {
		return this.code;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Automatable getWebPage() {
		return this.webpage;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
