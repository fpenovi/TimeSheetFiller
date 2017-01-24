package view.managers;

import model.webpages.WebPage;
import view.pages.timesheet.TimeSheetLoginView;

public class TimeSheetViewManager extends ProductViewManager {
	
	
	public TimeSheetViewManager() {
		super();
		this.webPage = WebPage.TIMESHEET.getWebPage();
		// this.webPage.create();
		this.views.add(new TimeSheetLoginView());
		// TODO Agregar el resto de las vistas!
	}

}
