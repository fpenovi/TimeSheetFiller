package view.layouts;

import controller.appcontainer.ExitAppHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ApplicationContainer extends BorderPane {
	
	private Stage stage;
	private ApplicationMenuBar menuBar;
	private StackPane pageContainer;
	private ApplicationLayout currentPage;
	
	
	public ApplicationContainer(Stage stage) {
		this.stage = stage;
		stage.setResizable(false);
		stage.setOnCloseRequest(e -> {	new ExitAppHandler().handle(new ActionEvent());	});
		this.stage.setTitle("Web Page Automation");
		this.menuBar = new ApplicationMenuBar();
		this.pageContainer = new StackPane();
		this.setTop(this.menuBar);
		this.setCenter(this.pageContainer);
	}
	
	
	public void switchView(ApplicationLayout newView) {		
		
		if (!this.hasPageShowing()) {	
			this.pageContainer.getChildren().add(newView);
			this.currentPage = newView;
			
			newView.fadeIn(callBack -> {
				newView.updateHeader();
			});
			
			return;
		}
		
		this.pageContainer.getChildren().add(0, newView);
		newView.setVisible(false);
		
		this.currentPage.fadeOut(callBack -> {			
			this.pageContainer.getChildren().remove(this.currentPage);
			newView.setVisible(true);
			newView.fadeIn(callBack2 -> {
				this.currentPage = newView;
				newView.updateHeader();
			});			
		});
		
	}
	
	
	public Stage getStage() {
		return this.stage;
	}
	
	
	private Boolean hasPageShowing() {
		return (this.pageContainer.getChildren().size() >= 1);
	}

}
