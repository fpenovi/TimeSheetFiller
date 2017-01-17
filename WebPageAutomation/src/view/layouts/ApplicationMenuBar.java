package view.layouts;

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
		this.file.getItems().add(new MenuItem("Exit"));
		this.help.getItems().add(new MenuItem("About"));		
	}
	
}
