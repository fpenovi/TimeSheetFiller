package controller.utils;

import javafx.scene.control.TextFormatter;

public class TextCharacterLimiter extends TextFormatter<String> {	
	
	public TextCharacterLimiter(int limit) {
		super(change -> change.getControlNewText().length() <= limit ? change : null);
	}

}
