package prv.felix.javacourses.swtviews;

import prv.felix.javacourses.interfaces.IClient;

public class SwtMainView implements IClient {

	private Controller controller;

	public SwtMainView(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void show() {
		System.out.println("test");

	}

}
