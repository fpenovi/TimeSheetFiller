package controller.appcontainer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ExitAppHandler implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		// TODO antes de ejecutar esto se tienen que liberar recursos (archivos abiertos)
		// cerrar el virtual-browser, etc
		Platform.exit();
	}
	
	public void handle() {		
		this.handle(new ActionEvent());
	}

}
