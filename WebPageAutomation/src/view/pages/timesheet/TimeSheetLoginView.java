package view.pages.timesheet;

import controller.utils.TextCharacterLimiter;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import view.layouts.ApplicationLayout;

public class TimeSheetLoginView extends ApplicationLayout {
	
	private TextField username;
	private PasswordField password;
	private final int MAX_CHARACTERS = 25;
	
	
	public TimeSheetLoginView() {
		super();
		this.makeGoBackButton();
		this.buildLoginForm();
		this.getStylesheets().add(getClass().getResource("/CSS/login.css").toExternalForm());
	}


	@Override
	public void updateHeader() {
		this.title.setText("Login");
		this.description.setText("Provide your TimeSheet username and password.");
		this.getApplicationContainer().getStage().setTitle("TimeSheet Filler");
	}
	
	
	private void buildLoginForm() {
		GridPane loginContainer = new GridPane();
		loginContainer.setId("timesheet-login-container");
		this.username = new TextField();
		this.username.getStyleClass().add("login-input");
		this.password = new PasswordField();
		this.password.getStyleClass().add("login-input");
		this.username.setTextFormatter(new TextCharacterLimiter(MAX_CHARACTERS));
		this.password.setTextFormatter(new TextCharacterLimiter(MAX_CHARACTERS));
		Label userNameDesc = new Label("Username");
		Label passwordDesc = new Label("Password");
		
		loginContainer.addColumn(0, userNameDesc, passwordDesc);
		loginContainer.addColumn(1, this.username, this.password);
		this.layout.setCenter(loginContainer);
	}
	
	

}
