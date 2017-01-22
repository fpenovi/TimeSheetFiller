package view.managers;

import java.util.ArrayList;
import java.util.List;

import model.Automatable;
import view.layouts.ApplicationLayout;

public abstract class ProductViewManager {
	
	protected Automatable webPage;
	protected int currentView;
	protected List<ApplicationLayout> views;
	
	
	public ProductViewManager() {
		this.views = new ArrayList<>();
		this.currentView = 0;
	}
	
	
	public ApplicationLayout getCurrentView() {
		return this.views.get(this.currentView);
	}
	

	public ApplicationLayout getCurrentView(ApplicationLayout entryPoint) {
		this.views.get(this.currentView).setEntryPoint(entryPoint);;
		return this.getCurrentView();
	}

}
