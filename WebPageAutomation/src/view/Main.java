package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.layouts.ApplicationContainer;
import view.pages.ProductSelectionView;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
				
		ApplicationContainer appContainer = new ApplicationContainer(stage);
		ProductSelectionView entryView = new ProductSelectionView();

		Scene scene = new Scene(appContainer, 450, 650);
		stage.setScene(scene);
		
		appContainer.switchView(entryView);
		stage.show();
	}
	
	

}
