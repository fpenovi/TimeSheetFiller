package view.layouts;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class ApplicationLayout extends BorderPane {
	
	protected HBox header;
	protected HBox footer;
	protected Label title;
	protected Text description;
	protected Button goBack;
	
	
	public ApplicationLayout() {
		this.header = new HBox();
		this.footer = new HBox();
		buildHeader();
		this.setTop(this.header);
		this.setBottom(this.footer);
		this.getStylesheets().add(getClass().getResource("/CSS/application-container.css").toExternalForm());
	}
	
	
	public HBox getHeader() {
		return this.header;
	}
	
	
	public HBox getFooter() {
		return this.footer;
	}
	
	
	public ApplicationContainer getApplicationContainer() {
		return (ApplicationContainer) this.getScene().getRoot();
	}
	
	
	protected void makeGoBackButton() {
		this.goBack.setVisible(true);
	}
	
	
	public abstract void updateHeader();
	
	
	private void buildHeader() {		
		this.header.setId("header-container");
		this.title = new Label();
		this.title.setId("header-title");
		this.description = new Text();
		this.description.setId("header-description");
		this.description.setWrappingWidth(300);
		
		this.goBack = new Button("");
		this.goBack.getStyleClass().add("header-go-back-btn");
		this.goBack.setVisible(false);
		
		VBox vbTextContainer = new VBox();
		vbTextContainer.setId("title-description-container");
		vbTextContainer.getChildren().addAll(this.title, this.description);
		this.header.getChildren().addAll(this.goBack, vbTextContainer);
	}		

}
