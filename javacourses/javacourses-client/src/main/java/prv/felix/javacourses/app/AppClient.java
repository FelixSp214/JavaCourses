package prv.felix.javacourses.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import prv.felix.javacourses.controller.Controller;
import prv.felix.javacourses.rmi.IRmiService;
import prv.felix.javacourses.swtviews.SwtDetailView;
import prv.felix.javacourses.swtviews.SwtMainView;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AppClient {

	private static final Logger LOGGER = LogManager.getLogger(AppClient.class);

	public static void main(String[] args) {
		executeClient();
	}

	public static void executeClient() {
		try {
			Registry registry = LocateRegistry.getRegistry(8080);
			IRmiService rmi = (IRmiService) registry.lookup("RmiService.server");

			Controller controller = new Controller(rmi);
			SwtMainView mainView = new SwtMainView(controller, new SwtDetailView());
			mainView.show();
			LOGGER.debug("Client started...");
			System.out.println("Client started...");
		} catch (Exception e) {
			LOGGER.error("Client could not start");
		}

	}

}
