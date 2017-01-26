package controller.appcontainer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.layouts.ApplicationLayout;

public class GoBackHandler implements EventHandler<ActionEvent> {
	
	private ApplicationLayout callerPage;
	
	
	public GoBackHandler(ApplicationLayout callerPage) {
		this.callerPage = callerPage;
	}
	

	@Override
	public void handle(ActionEvent event) {
		callerPage.preventUserActions(true);
		callerPage.getApplicationContainer().switchView(callerPage.getPreviousPage());
	}

}
