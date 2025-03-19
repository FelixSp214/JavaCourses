package prv.felix.javacourses.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import prv.felix.javacourses.console.ConsoleUI;
import prv.felix.javacourses.controller.Controller;
import prv.felix.javacourses.rmi.IRmiService;
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
			Registry registry = LocateRegistry.getRegistry(8181);
			IRmiService rmi = (IRmiService) registry.lookup("RmiService.server");

			Controller controller = new Controller(rmi);
			ConsoleUI mainView = new ConsoleUI(controller);
			LOGGER.debug("Client started...");
			mainView.show();
		} catch (Exception e) {
            LOGGER.error("Client could not start {}", e.getMessage());
		}

	}

}
