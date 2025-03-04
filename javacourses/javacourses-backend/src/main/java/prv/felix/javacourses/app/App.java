package prv.felix.javacourses.app;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import prv.felix.javacourses.rmi.IRmiService;
import prv.felix.javacourses.services.DataServiceImpl;
import prv.felix.javacourses.services.JavaCoursesH2DaoImpl;
import prv.felix.javacourses.services.RmiService;

public class App {

    private static App app;
    private static final Logger LOGGER = LogManager.getLogger(App.class);

    private App() {}

    public static App getApp() {
        if (app == null) {
            return new App();
        }
        return app;
    }

    public static void execute() {
        try {
            String dbUrl = "Test";
            String userName = "Test";
            String password = "test";

            RmiService rmiService = new RmiService(
                    new DataServiceImpl(new JavaCoursesH2DaoImpl(dbUrl, userName, password)));
            IRmiService iRmiService = (IRmiService) UnicastRemoteObject.exportObject(rmiService, 0);
            Registry registry = LocateRegistry.createRegistry(8080);
            registry.bind("RmiService.server", iRmiService);
            LOGGER.debug("Rmi service started...");
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

}
