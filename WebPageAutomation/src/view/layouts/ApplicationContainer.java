package view.layouts;

import controller.appcontainer.ExitAppHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ApplicationContainer extends StackPane {
	
	private Stage stage;
	private ApplicationMenuBar menuBar;
	private BorderPane layout;
	private StackPane pageContainer;
	private ApplicationLayout currentPage;
	
	
	public ApplicationContainer(Stage stage, ApplicationLayout entryView) {
		this.stage = stage;
		stage.setResizable(false);
		this.stage.setTitle("Web Page Automation");
		stage.setOnCloseRequest(e -> {	new ExitAppHandler().handle(new ActionEvent());	});
		this.layout = new BorderPane();
		this.pageContainer = new StackPane();
		this.pageContainer.getChildren().add(entryView);
		this.menuBar = new ApplicationMenuBar();
		this.layout.setTop(this.menuBar);
		this.layout.setCenter(this.pageContainer);
		this.currentPage = entryView;
		this.getChildren().add(this.layout);
	}
	
	
	public void switchView(ApplicationLayout newView) {
		
		newView.setWhiteScreen(true);
		this.pageContainer.getChildren().add(0, newView);
		
		this.currentPage.whiteScreenOn(callBack -> {
			this.pageContainer.getChildren().remove(this.currentPage);
			newView.whiteScreenOff(event -> {
				this.currentPage = newView;
				newView.setWhiteScreen(false);
			});
		});
				
		newView.updateHeader();		
	}
	
	
	public Stage getStage() {
		return this.stage;
	}

}
