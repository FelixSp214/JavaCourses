package prv.felix.javacourses.swtviews;

import prv.felix.javacourses.controller.Controller;
import prv.felix.javacourses.interfaces.IClient;

public class SwtMainView implements IClient {

	private Controller controller;
	private SwtDetailView detailView;

	public SwtMainView(Controller controller, SwtDetailView detailView) {
		this.controller = controller;
		this.detailView = detailView;
	}

	@Override
	public void show() {
		System.out.println("test");

	}

}
