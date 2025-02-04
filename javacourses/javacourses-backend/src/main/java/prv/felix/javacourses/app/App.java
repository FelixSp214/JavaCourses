package prv.felix.javacourses.app;

import java.rmi.Naming;

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
			Naming.rebind("rmi://localhost:1099/RmiService", rmiService);
			System.out.println("RmiService is running...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
