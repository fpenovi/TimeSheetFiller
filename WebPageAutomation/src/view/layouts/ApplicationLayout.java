package view.layouts;

import java.util.Arrays;
import java.util.List;

import controller.appcontainer.GoBackHandler;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public abstract class ApplicationLayout extends BorderPane {
	
	protected HBox header;
	protected HBox footer;
	protected Label title;
	protected Text description;
	protected Button goBack;
	protected Double titleOpacity;
	protected Double descriptionOpacity;
	protected Double goBackOpacity;
	protected Boolean opacitiesSaved = false;
	protected Group fadeables;
	protected ApplicationLayout previousPage;
	private final Double FADE_SPEED = 1000.0;
	
	
	public ApplicationLayout() {
		this.header = new HBox();
		this.footer = new HBox();
		buildHeader();		
		this.setTop(this.header);
		this.setBottom(this.footer);
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
	
	
	public void fadeIn(EventHandler<ActionEvent> callBack) {
		
		ParallelTransition fadeIn = new ParallelTransition();
		fadeIn.getChildren().addAll(makeFadeInFX());
		fadeIn.setOnFinished(callBack);
		fadeIn.play();
	}
	
	
	public void fadeOut(EventHandler<ActionEvent> callBack) {
		
		ParallelTransition fadeOut = new ParallelTransition();
		fadeOut.getChildren().addAll(makeFadeOutFX());
		fadeOut.setOnFinished(callBack);
		fadeOut.play();
	}
	
	
	protected void makeGoBackButton() {
		this.goBack.setVisible(true);
	}
	
	
	public abstract void updateHeader();
	
	
//	************** PRIVATE METHODS **************
	
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
		this.goBack.setOnAction(new GoBackHandler(this));		
		
		VBox vbTextContainer = new VBox();
		vbTextContainer.setId("title-description-container");
		vbTextContainer.getChildren().addAll(this.title, this.description);
		this.header.getChildren().addAll(this.goBack, vbTextContainer);
	}
	
	
	private List<FadeTransition> makeFadeInFX() {
		
		if (!opacitiesSaved)
			saveOpacities();
		
		FadeTransition fadeInTitle = new FadeTransition(Duration.millis(FADE_SPEED), this.title);
		FadeTransition fadeInDesc = new FadeTransition(Duration.millis(FADE_SPEED), this.description);
		FadeTransition fadeInGoBack = new FadeTransition(Duration.millis(FADE_SPEED), this.goBack);
		FadeTransition fadeInPage = new FadeTransition(Duration.millis(FADE_SPEED), this.getCenter());
		fadeInTitle.setFromValue(0);
		fadeInTitle.setToValue(this.titleOpacity);
		fadeInDesc.setFromValue(0);
		fadeInDesc.setToValue(this.descriptionOpacity);
		fadeInGoBack.setFromValue(0);
		fadeInGoBack.setToValue(this.goBackOpacity);
		fadeInPage.setFromValue(0);
		fadeInPage.setToValue(1);
		
		return Arrays.asList(fadeInTitle, fadeInDesc, fadeInGoBack, fadeInPage);
	}
	
	
	private List<FadeTransition> makeFadeOutFX() {
		
		if (!opacitiesSaved)
			saveOpacities();
		
		FadeTransition fadeOutTitle = new FadeTransition(Duration.millis(FADE_SPEED), this.title);
		FadeTransition fadeOutDesc = new FadeTransition(Duration.millis(FADE_SPEED), this.description);
		FadeTransition fadeOutGoBack = new FadeTransition(Duration.millis(FADE_SPEED), this.goBack);
		FadeTransition fadeOutPage = new FadeTransition(Duration.millis(FADE_SPEED), this.getCenter());
		fadeOutTitle.setFromValue(this.titleOpacity);
		fadeOutTitle.setToValue(0);
		fadeOutDesc.setFromValue(this.descriptionOpacity);
		fadeOutDesc.setToValue(0);
		fadeOutGoBack.setFromValue(this.goBackOpacity);
		fadeOutGoBack.setToValue(0);
		fadeOutPage.setFromValue(1);
		fadeOutPage.setToValue(0);
		
		return Arrays.asList(fadeOutTitle, fadeOutDesc, fadeOutGoBack, fadeOutPage);
	}
	
	
	private void saveOpacities() {
		this.applyCss();
		this.titleOpacity = this.title.getOpacity();
		this.descriptionOpacity = this.description.getOpacity();
		this.goBackOpacity = this.goBack.getOpacity();
		this.opacitiesSaved = true;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((previousPage == null) ? 0 : previousPage.hashCode());
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
		if (previousPage == null) {
			if (other.previousPage != null)
				return false;
		} else if (!previousPage.equals(other.previousPage))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	
}
