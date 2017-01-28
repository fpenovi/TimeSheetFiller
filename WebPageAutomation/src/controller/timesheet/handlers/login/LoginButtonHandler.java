package controller.timesheet.handlers.login;

import controller.timesheet.services.TimeSheetLoginService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.layouts.ApplicationLayout;

public class LoginButtonHandler implements EventHandler<ActionEvent> {
	
	private ApplicationLayout callerPage;
	
	
	public LoginButtonHandler(ApplicationLayout callerPage) {
		this.callerPage = callerPage;
	}
	

	@Override
	public void handle(ActionEvent event) {
		
		TimeSheetLoginService loginService = new TimeSheetLoginService(null);	// TODO fix this
		
		loginService.setOnRunning(callBack -> {
			this.callerPage.showLoadingModal(true);
		});
		
		loginService.setOnSucceeded(callBack -> {
			this.callerPage.showLoadingModal(false);
		});
		
		loginService.start();
	}

}
