package controller.productselection;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.webpages.WebPage;
import view.layouts.ApplicationLayout;

public class ProductSelectHandler implements EventHandler<ActionEvent> {
	
	ApplicationLayout callerPage;
	
	public ProductSelectHandler(ApplicationLayout page) {
		this.callerPage = page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handle(ActionEvent event) {
		HBox header = callerPage.getHeader();
		Label title = (Label) header.lookup("#header-title");
		Text description = (Text) header.lookup("#header-description");
		ComboBox<WebPage> select = (ComboBox<WebPage>) callerPage.lookup("#combobox-products");
		title.setText(select.getSelectionModel().getSelectedItem().toString());
		description.setText(select.getSelectionModel().getSelectedItem().getDescription());
	}

}
