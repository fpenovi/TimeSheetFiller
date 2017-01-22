package controller;

import java.util.HashMap;
import java.util.Map;

import model.webpages.WebPage;
import view.managers.ProductViewManager;
import view.managers.TimeSheetViewManager;

public class GUIManager {
	
	private static GUIManager instance = new GUIManager();
	private Map<String, ProductViewManager> productViews; 
	
	private GUIManager() {
		this.productViews = new HashMap<>();
		this.productViews.put(WebPage.TIMESHEET.getCode(), new TimeSheetViewManager());
	}
	
	public static GUIManager getInstance() {
		return instance;
	}
	
	public ProductViewManager getProductViewManager(String productCode) {
		return this.productViews.get(productCode);
	}

}
