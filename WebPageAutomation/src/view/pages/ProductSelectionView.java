package view.pages;

import controller.productselection.ProductSelectHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import model.webpages.WebPage;
import view.layouts.ApplicationLayout;

public class ProductSelectionView extends ApplicationLayout {
	
	private ComboBox<WebPage> productSelecter;
	private Button nextButton;
	
	public ProductSelectionView() {
		super();
		this.productSelecter = new ComboBox<>();
		this.productSelecter.setOnAction(new ProductSelectHandler(this));
		this.productSelecter.getItems().addAll(WebPage.getListOfWebPages());
		this.productSelecter.getItems().sort((WebPage w1, WebPage w2) -> w1.toString().compareTo(w2.toString()));
		this.setCenter(this.productSelecter);
		this.productSelecter.getSelectionModel().select(0);
	}

}
