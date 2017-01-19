package controller.productselection;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.layouts.ApplicationLayout;

public class ProductSelectHandler implements EventHandler<ActionEvent> {
	
	ApplicationLayout callerPage;
	
	public ProductSelectHandler(ApplicationLayout page) {
		this.callerPage = page;
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Do iT!
		// Obtener el titulo y la descripcion del producto seleccionado y mostrarlos
		// Cambiar el setOnAction del boton Next por el handler adecuado
	}

}
