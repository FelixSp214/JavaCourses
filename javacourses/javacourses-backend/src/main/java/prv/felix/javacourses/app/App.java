package prv.felix.javacourses.app;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import prv.felix.javacourses.services.RmiService;

public class App {

	private static App app;

	private App() {
	}

	public static App test() {
		if (app == null) {
			return new App();
		}
		return app;
	}

	public static void execute() {
		try {
			RmiService rmiService = new RmiService();
			LocateRegistry.createRegistry(8080);
			System.out.println("RmiService is running...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
