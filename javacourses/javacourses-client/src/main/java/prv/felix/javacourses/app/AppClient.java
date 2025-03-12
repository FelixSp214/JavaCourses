package prv.felix.javacourses.app;

import prv.felix.javacourses.controller.Controller;
import prv.felix.javacourses.rmi.IRmiService;
import prv.felix.javacourses.swtviews.SwtDetailView;
import prv.felix.javacourses.swtviews.SwtMainView;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AppClient {

	public static void main(String[] args) throws RemoteException {
		executeClient();
	}

	public static void executeClient() {
		try {
			Registry registry = LocateRegistry.getRegistry(8080);
			IRmiService rmi = (IRmiService) registry.lookup("IRmiService.server");

			Controller controller = new Controller(rmi);
			SwtMainView mainView = new SwtMainView(controller, new SwtDetailView());
			mainView.show();
		} catch (Exception e) {
			System.out.println("test");
		}

	}

}
