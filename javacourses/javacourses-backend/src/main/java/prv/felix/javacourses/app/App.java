package prv.felix.javacourses.app;

import java.rmi.registry.LocateRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
            RmiService rmiService = new RmiService();
            LocateRegistry.createRegistry(8080);
            LOGGER.debug("Rmi service started...");
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

}
