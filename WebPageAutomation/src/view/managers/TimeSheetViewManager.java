package view.managers;

import model.webpages.WebPage;
import view.pages.timesheet.TimeSheetLogin;

public class TimeSheetViewManager extends ProductViewManager {
	
	
	public TimeSheetViewManager() {
		super();
		this.webPage = WebPage.TIMESHEET.getWebPage();
		this.webPage.create();
		this.views.add(new TimeSheetLogin());
	}

}
