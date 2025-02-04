package prv.felix.javacourses.app;

import prv.felix.javacourses.swtviews.Controller;
import prv.felix.javacourses.swtviews.SwtMainView;

public class AppClient {

	public void executeClient() {
		Controller controller = new Controller();
		SwtMainView mainView = new SwtMainView(controller);
		mainView.show();
	}

}
