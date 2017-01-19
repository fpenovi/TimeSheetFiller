package view.layouts;

import controller.appcontainer.ExitAppHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class ApplicationMenuBar extends MenuBar {
	
	private Menu file;
	private Menu help;

	public ApplicationMenuBar() {
		super();
		this.file = new Menu("File");
		this.help = new Menu("Help");
		populateOptions();
		this.getMenus().addAll(file, help);
	}
	
	
	private void populateOptions() {
		
		// File
		MenuItem exitButton = new MenuItem("Exit");
		exitButton.setOnAction(new ExitAppHandler());
		this.file.getItems().add(exitButton);
		
		// Help
		this.help.getItems().add(new MenuItem("About"));
	}
	
}
