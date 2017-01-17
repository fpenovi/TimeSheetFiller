package view;

import javafx.scene.control.ComboBox;
import model.webpages.WebPage;
import view.layouts.ApplicationLayout;

public class ProductSelectionView extends ApplicationLayout {
	
	private ComboBox<WebPage> productSelecter;
	
	public ProductSelectionView() {
		super();
		this.productSelecter = new ComboBox<>();
		this.productSelecter.getItems().addAll(WebPage.getListOfWebPages());
		this.productSelecter.getItems().sort((WebPage w1, WebPage w2) -> w1.getDescription().compareTo(w2.getDescription()));
		this.setCenter(this.productSelecter);
	}

}
