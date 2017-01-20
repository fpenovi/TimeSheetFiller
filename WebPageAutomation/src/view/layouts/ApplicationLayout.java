package view.layouts;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class ApplicationLayout extends BorderPane {
	
	private HBox header;
	private HBox footer;
	private Label title;
	private Text description;
	
	
	public ApplicationLayout() {
		this.header = new HBox();
		this.footer = new HBox();
		buildHeader();
		this.setTop(this.header);
		this.setBottom(this.footer);
	}
	
	
	public HBox getHeader() {
		return this.header;
	}
	
	
	public HBox getFooter() {
		return this.footer;
	}
	
	
	private void buildHeader() {
		this.header.setId("header-container");
		this.title = new Label();
		this.description = new Text();
		this.description.setWrappingWidth(300);
		VBox vbTextContainer = new VBox(10);
		vbTextContainer.setPadding(new Insets(20, 0, 0, 20));
		vbTextContainer.getChildren().addAll(this.title, this.description);
		this.title.setId("header-title");
		this.description.setId("header-description");
		this.header.getChildren().add(vbTextContainer);
		this.getStylesheets().add(getClass().getResource("application-container.css").toExternalForm());
	}

}
