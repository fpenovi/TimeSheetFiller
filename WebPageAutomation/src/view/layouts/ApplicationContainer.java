package view.layouts;

import controller.appcontainer.ExitAppHandler;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ApplicationContainer extends StackPane {
	
	private Stage stage;
	private BorderPane layout;
	private ApplicationMenuBar menuBar;
	private FadeTransition fadeIn;
	private FadeTransition fadeOut;
	private TranslateTransition translateRight;
	private TranslateTransition translateLeft;
	private ParallelTransition nextTransition;
	private ParallelTransition goBackTransition;
	
	
	public ApplicationContainer(Stage stage) {
		this.stage = stage;
		stage.setResizable(false);
		this.stage.setTitle("Web Page Automation");
		stage.setOnCloseRequest(e -> {	new ExitAppHandler().handle(new ActionEvent());	});
		this.layout = new BorderPane();
		this.menuBar = new ApplicationMenuBar();
		this.layout.setTop(this.menuBar);
		buildFadeEffects();
		this.getChildren().add(this.layout);
	}
	
	
	public void switchView(ApplicationLayout newView) {			
		
		Rectangle rect = new Rectangle(70, 70);
		rect.setFill(Color.BLUEVIOLET);
		
		Circle circ = new Circle(50);
		circ.setFill(Color.AQUA);
		
		rect.setOpacity(0);
		this.getChildren().add(rect);
		this.getChildren().add(circ);
		
		
		this.fadeIn.setNode(rect);
		this.translateRight.setNode(rect);
		this.fadeOut.setNode(circ);
		this.translateLeft.setNode(circ);
		
		nextTransition.play();
		goBackTransition.play();
		
		this.layout.setCenter(newView);
		newView.updateHeader();
		
	}
	
	
	public Stage getStage() {
		return this.stage;
	}
	
	
	private void buildFadeEffects() {
		this.fadeOut = new FadeTransition(Duration.millis(2000));
		this.fadeOut.setToValue(0);
		this.fadeIn = new FadeTransition(Duration.millis(2000));
		this.fadeIn.setToValue(100);
		this.translateRight = new TranslateTransition(Duration.millis(2000));
		this.translateRight.setToX(-225);
		this.translateLeft = new TranslateTransition(Duration.millis(2000));
		this.translateLeft.setToX(225);
		
		this.nextTransition = new ParallelTransition(this.fadeOut, this.fadeIn, this.translateRight);
		this.goBackTransition = new ParallelTransition(this.fadeOut, this.fadeIn, this.translateLeft);
	}

}
