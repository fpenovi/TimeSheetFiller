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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import view.utils.Constants;

public abstract class ApplicationLayout extends StackPane {
	
	protected BorderPane miniPageContainer;
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
	private VBox clickBlocker;
	private VBox loadingModal;
	private Label loadingLabel;
	
	
	public ApplicationLayout() {
		this.miniPageContainer = new BorderPane();
		this.getChildren().add(this.miniPageContainer);
		this.header = new HBox();
		this.footer = new HBox();
		buildHeader();		
		this.miniPageContainer.setTop(this.header);
		this.miniPageContainer.setBottom(this.footer);
		buildModals();
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
	
	
	public void preventUserActions(Boolean prevent) {
		this.clickBlocker.setVisible(prevent);
	}
	
	
	public Boolean isLoading() {
		return this.loadingModal.isVisible();
	}
	
	
	protected void makeGoBackButton() {
		this.goBack.setVisible(true);
	}
	
	
	public void showLoadingModal(Boolean show) {
		
		if (show && this.clickBlocker.isVisible())
			return;
		
		this.loadingModal.setVisible(show);
		
		ParallelTransition showModal = new ParallelTransition();
		FadeTransition fadeScreen = new FadeTransition(Duration.millis(Constants.APP_LOADING_MODAL_ANIMATION), this.clickBlocker);
		FadeTransition fadeSpinner = new FadeTransition(Duration.millis(Constants.APP_LOADING_MODAL_ANIMATION), this.loadingModal);
		
		if (show) {
			this.clickBlocker.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
			fadeScreen.setFromValue(0);
			fadeScreen.setToValue(0.7);
			fadeSpinner.setFromValue(0);
			fadeSpinner.setToValue(1);
		}
		
		else {			
			fadeScreen.setFromValue(0.7);
			fadeScreen.setToValue(0);
			fadeSpinner.setFromValue(1);
			fadeSpinner.setToValue(0);
		}
		
		showModal.getChildren().addAll(fadeScreen, fadeSpinner);
		
		if (!show)
			showModal.setOnFinished(callback -> {
				this.clickBlocker.setVisible(false);
				this.loadingModal.setVisible(false);
				this.clickBlocker.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
			});
		
		showModal.play();
		
		if (show)
			this.clickBlocker.setVisible(true);
		
		this.disableFocus(show);
	}


	public abstract String getAppTitle();
	protected abstract void disableFocus(Boolean disable);
	
	
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
		
		FadeTransition fadeInTitle = new FadeTransition(Duration.millis(Constants.APP_SWITCH_PAGE_SPEED), this.title);
		FadeTransition fadeInDesc = new FadeTransition(Duration.millis(Constants.APP_SWITCH_PAGE_SPEED), this.description);
		FadeTransition fadeInGoBack = new FadeTransition(Duration.millis(Constants.APP_SWITCH_PAGE_SPEED), this.goBack);
		FadeTransition fadeInPage = new FadeTransition(Duration.millis(Constants.APP_SWITCH_PAGE_SPEED), this.miniPageContainer.getCenter());
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
		
		FadeTransition fadeOutTitle = new FadeTransition(Duration.millis(Constants.APP_SWITCH_PAGE_SPEED), this.title);
		FadeTransition fadeOutDesc = new FadeTransition(Duration.millis(Constants.APP_SWITCH_PAGE_SPEED), this.description);
		FadeTransition fadeOutGoBack = new FadeTransition(Duration.millis(Constants.APP_SWITCH_PAGE_SPEED), this.goBack);
		FadeTransition fadeOutPage = new FadeTransition(Duration.millis(Constants.APP_SWITCH_PAGE_SPEED), this.miniPageContainer.getCenter());
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
	
	
	private void buildModals() {
		this.clickBlocker = new VBox();
		this.clickBlocker.setMinSize(Constants.APP_WIDTH, Constants.APP_HEIGHT);
		this.clickBlocker.setVisible(false);	
				
		this.loadingLabel = new Label("Loading...");
		this.loadingLabel.setId("loading-label");
		this.loadingModal = new VBox(new ImageView(new Image(getClass().getResource("/images/load-animation.gif").toExternalForm())), this.loadingLabel);
		this.loadingModal.setVisible(false);
		this.loadingModal.getStyleClass().add("loading-modal");

		this.clickBlocker.getChildren().add(this.loadingModal);
		this.getChildren().add(this.clickBlocker);
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
