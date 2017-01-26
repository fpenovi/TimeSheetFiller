package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.layouts.ApplicationContainer;
import view.pages.ProductSelectionView;
import view.utils.Constants;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
				
		ApplicationContainer appContainer = new ApplicationContainer(stage);
		ProductSelectionView entryView = new ProductSelectionView();

		Scene scene = new Scene(appContainer, Constants.APP_WIDTH, Constants.APP_HEIGHT);
		stage.setScene(scene);
		
		stage.show();
		appContainer.switchView(entryView);
	}
	
	

}
