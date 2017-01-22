package view.pages.timesheet;

import view.layouts.ApplicationLayout;

public class TimeSheetLogin extends ApplicationLayout {
	
	
	public TimeSheetLogin() {
		super();
		this.makeGoBackButton();
	}
	
	
	@Override
	public void updateHeader() {
		this.title.setText("Login");
		this.description.setText("Provide your TimeSheet username and password.");
		this.getApplicationContainer().getStage().setTitle("TimeSheet Filler");
	}

}
