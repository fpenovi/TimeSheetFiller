package view.layouts;

import controller.appcontainer.GoBackHandler;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public abstract class ApplicationLayout extends StackPane {
	
	protected BorderPane layout;
	protected HBox header;
	protected HBox footer;
	protected Label title;
	protected Text description;
	protected Button goBack;
	protected ApplicationLayout previousPage;
	protected VBox animationModal;
	protected FadeTransition fadeIn;
	protected FadeTransition fadeOut;
	
	
	
	public ApplicationLayout() {
		this.header = new HBox();
		this.footer = new HBox();
		this.layout = new BorderPane();
		buildHeader();
		this.layout.setTop(this.header);
		this.layout.setBottom(this.footer);
		makeModals();
		this.getChildren().addAll(this.layout, this.animationModal);
		this.getStylesheets().add(getClass().getResource("/CSS/application-layout.css").toExternalForm());
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
	
	
	public ApplicationLayout getPreviousPage() {
		return this.previousPage;
	}
	
	
	public void setEntryPoint(ApplicationLayout entryPoint) {
		this.previousPage = entryPoint;
	}
	
	
	public void setWhiteScreen(Boolean on) {
		this.animationModal.setDisable(!on);
		
		if (on && this.animationModal.getOpacity() < 100)
			this.animationModal.setOpacity(100);
		
		else
			this.animationModal.setOpacity(0);
	}
	
	
	public void whiteScreenOn(EventHandler<ActionEvent> callBack) {
		if (!this.animationModal.isDisabled())
			return;
		
		
		this.animationModal.setDisable(false);
		ParallelTransition transition = new ParallelTransition();
		transition.getChildren().add(this.fadeIn);
		transition.setOnFinished(callBack);
		transition.play();
	}
	
	
	public void whiteScreenOff(EventHandler<ActionEvent> callBack) {
		if (this.animationModal.isDisabled())
			return;
		
		ParallelTransition transition = new ParallelTransition();
		transition.getChildren().add(this.fadeOut);
		transition.setOnFinished(callBack);
		
		transition.play();
	}
	
	
	protected void makeGoBackButton() {
		this.goBack.setVisible(true);
	}
	
	
	public abstract void updateHeader();
	
	
	// ************** PRIVATE METHODS **************
	
	
	private void buildHeader() {		
		this.header.setId("header-container");
		this.title = new Label();
		this.title.setId("header-title");
		this.description = new Text();
		this.description.setId("header-description");
		this.description.setWrappingWidth(300);
		
		buildGoBackButton();				
		
		VBox vbTextContainer = new VBox();
		vbTextContainer.setId("title-description-container");
		vbTextContainer.getChildren().addAll(this.title, this.description);
		this.header.getChildren().addAll(this.goBack, vbTextContainer);
	}
	
	
	private void buildGoBackButton() {
		this.goBack = new Button("");
		this.goBack.getStyleClass().add("header-go-back-btn");
		this.goBack.setVisible(false);
		this.goBack.setOnAction(new GoBackHandler(this));		
	}
	
	
	private void makeModals() {
		this.animationModal = new VBox();
		this.animationModal.setId("animation-modal");
		this.animationModal.setOpacity(0);
		this.animationModal.setDisable(true);
		this.fadeIn = new FadeTransition(Duration.millis(400), this.animationModal);
		this.fadeIn.setFromValue(0);
		this.fadeIn.setToValue(100);
		this.fadeOut = new FadeTransition(Duration.millis(400), this.animationModal);
		this.fadeOut.setFromValue(100);
		this.fadeOut.setToValue(0);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApplicationLayout other = (ApplicationLayout) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


	


	
	
	
	

}
