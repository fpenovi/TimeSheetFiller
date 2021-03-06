package view.layouts;

import controller.appcontainer.ExitAppHandler;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.utils.Constants;

public class ApplicationContainer extends BorderPane {
	
	private Stage stage;
	private ApplicationMenuBar menuBar;
	private StackPane pageContainer;
	private ApplicationLayout currentPage;
	
	
	public ApplicationContainer(Stage stage) {
		this.stage = stage;
		stage.setResizable(false);
		stage.setOnCloseRequest(e -> {	new ExitAppHandler().handle(new ActionEvent());	});
		this.stage.setTitle(Constants.APP_TITLE);
		this.stage.getIcons().add(new Image(this.getClass().getResource("/images/hiRes.png").toExternalForm()));
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
				newView.getAppTitle();
			});
			
			return;
		}
		
		this.pageContainer.getChildren().add(0, newView);
		newView.setVisible(false);
		
		this.currentPage.fadeOut(callBack -> {			
			this.pageContainer.getChildren().remove(this.currentPage);
			newView.setVisible(true);
			this.stage.setTitle(newView.getAppTitle());
			newView.fadeIn(callBack2 -> {
				this.currentPage = newView;
				this.currentPage.preventUserActions(false);
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
