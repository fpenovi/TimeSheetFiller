package view.layouts;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ApplicationContainer extends BorderPane {
	
	private Stage stage;
	private ApplicationMenuBar menuBar;
	
	public ApplicationContainer(Stage stage) {
		this.stage = stage;
		this.stage.setTitle("WebPageAutomation");
		stage.setResizable(false);
		this.menuBar = new ApplicationMenuBar();
		this.setTop(this.menuBar);
	}
	
	public void switchView(ApplicationLayout newView) {
		this.setCenter(newView);
	}

}
