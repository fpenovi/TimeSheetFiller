package view.pages;

import controller.productselection.NextButtonHandler;
import controller.productselection.ProductSelectHandler;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import model.webpages.WebPage;
import view.layouts.ApplicationLayout;

public class ProductSelectionView extends ApplicationLayout {
	
	private VBox container;
	private ComboBox<WebPage> productSelecter;
	private Button nextButton;
	
	public ProductSelectionView() {
		super();			
		populateProductSelecter();
		setUpNextButton();
		setUpContainer();		
		this.miniPageContainer.getChildren().add(0, container);
		this.getStylesheets().add(getClass().getResource("/CSS/product-selection.css").toExternalForm());
		Event.fireEvent(this.productSelecter, new ActionEvent());	// Applies the change of selecting the first element
	}
	
	
	@Override
	public void updateHeader() {
		this.getApplicationContainer().getStage().setTitle("Web Page Automation");
	}
		

	private void populateProductSelecter() {
		this.productSelecter = new ComboBox<>();
		this.productSelecter.setOnAction(new ProductSelectHandler(this));
		this.productSelecter.setId("combobox-products");	
		this.productSelecter.getItems().addAll(WebPage.getListOfWebPages());
		this.productSelecter.getItems().sort((WebPage w1, WebPage w2) -> w1.toString().compareTo(w2.toString()));
		this.productSelecter.getSelectionModel().selectFirst();
	}
	
	
	private void setUpNextButton() {
		this.nextButton = new Button("Next");
		this.nextButton.setOnAction(new NextButtonHandler(this));
	}
	
	
	private void setUpContainer() {
		this.container = new VBox();
		this.container.setId("select-container");
		this.container.getChildren().addAll(this.productSelecter, this.nextButton);
	}

}
