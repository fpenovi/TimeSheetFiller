package view.pages.timesheet;

import controller.utils.TextCharacterLimiter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import view.layouts.ApplicationLayout;
import view.utils.Constants;

public class TimeSheetLoginView extends ApplicationLayout {
	
	private TextField username;
	private PasswordField password;
	private final int MAX_CHARACTERS = 25;
	private Button logInBtn;
	
	
	public TimeSheetLoginView() {
		super();
		this.makeGoBackButton();
		this.buildLoginForm();
		this.title.setText("Login");
		this.description.setText("Provide your TimeSheet username and password.");
		this.getStylesheets().add(getClass().getResource("/CSS/login.css").toExternalForm());
	}


	@Override
	public String getAppTitle() {		
		return Constants.TIMESHEET_TITLE;
	}
	
	
	@Override
	protected void disableFocus(Boolean disable) {
		this.requestFocus();
		this.username.setFocusTraversable(!disable);
		this.password.setFocusTraversable(!disable);
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
		userNameDesc.getStyleClass().add("timesheet-login-label");
		passwordDesc.getStyleClass().add("timesheet-login-label");
		
		this.logInBtn = new Button("Log In");
		this.logInBtn.setId("timesheet-login-btn");
		this.logInBtn.getStyleClass().add("master-btn");
		
		this.logInBtn.setOnAction(event -> {
			
			if (!this.isLoading())			
				this.showLoadingModal(true);
		});
		
		loginContainer.addColumn(0, userNameDesc, passwordDesc);
		loginContainer.addColumn(1, this.username, this.password);
		loginContainer.add(this.logInBtn, 1, 2);
		this.miniPageContainer.setCenter(loginContainer);		
	}	

}
