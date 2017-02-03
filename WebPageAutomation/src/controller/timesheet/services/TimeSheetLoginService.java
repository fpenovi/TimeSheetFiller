package controller.timesheet.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import model.Automatable;

public class TimeSheetLoginService extends Service<String> {
	
	private Automatable webPage;
	
	public TimeSheetLoginService(Automatable webPage) {
		this.webPage = webPage;
	}

	@Override
	protected Task<String> createTask() {
		
		return new Task<String>() {

			@Override
			protected String call() throws Exception {
				Thread.sleep(5000);		// Simulating load time
				return "OK";
			}
			
		};
		
	}

}
