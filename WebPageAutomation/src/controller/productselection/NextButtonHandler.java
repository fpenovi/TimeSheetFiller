package controller.productselection;

import controller.GUIManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import model.webpages.WebPage;
import view.layouts.ApplicationContainer;
import view.layouts.ApplicationLayout;
import view.managers.ProductViewManager;

public class NextButtonHandler implements EventHandler<ActionEvent> {
	
	private ApplicationLayout callerPage;
	
	public NextButtonHandler(ApplicationLayout page) {
		this.callerPage = page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handle(ActionEvent event) {
		// TODO obtener el valor del select y pedirle al singleton de vistas que me de la apropiada y switcheo a esa
		ComboBox<WebPage> select = (ComboBox<WebPage>) callerPage.lookup("#combobox-products");
		ProductViewManager chosenProductManager = GUIManager.getInstance().getProductView(select.getSelectionModel().getSelectedItem().getCode());
		ApplicationContainer appContainer = (ApplicationContainer) callerPage.getScene().getRoot();
		appContainer.switchView(chosenProductManager.getCurrentView());
	}

}
