package prv.felix.javacourses.app;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import prv.felix.javacourses.rmi.IRmiService;
import prv.felix.javacourses.services.DataServiceImpl;
import prv.felix.javacourses.services.JavaCoursesH2DaoImpl;
import prv.felix.javacourses.services.RmiService;

public class App {

    private static App app;
    private static final Logger LOGGER = LogManager.getLogger(App.class);

    private App() {
    }

    public static App getApp() {
        if (app == null) {
            return new App();
        }
        return app;
    }

    public static void execute() {
        try {
            String[] propertiesArray = loadProperties();
            assert propertiesArray != null;
            String url = propertiesArray[0];
            String user = propertiesArray[1];
            String password = propertiesArray[2];

            RmiService rmiService = new RmiService(
                    new DataServiceImpl(new JavaCoursesH2DaoImpl(url, user, password)));
            IRmiService iRmiService = (IRmiService) UnicastRemoteObject.exportObject(rmiService, 0);
            Registry registry = LocateRegistry.createRegistry(8080);
            registry.bind("RmiService.server", iRmiService);
            LOGGER.debug("Rmi service started...");
            System.out.println("Rmi service started...");
        } catch (Exception e) {
            LOGGER.error("Rmi service could not start...", e.getLocalizedMessage());
        }
    }

    private static String[] loadProperties() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = App.class.getClassLoader().getResourceAsStream("conf/application.properties")) {
            if (input == null) {
                System.err.println("Datei 'conf/config.properties' nicht gefunden!");
                throw new IOException();
            }
            properties.load(input);

            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            String[] propertiesArray = new String[3];
            propertiesArray[0] = url;
            propertiesArray[1] = user;
            propertiesArray[2] = password;

            return propertiesArray;

        } catch (Exception e) {
            System.err.println("Fehler beim Laden der Properties-Datei: " + e.getMessage());
        }

        return null;
    }

}
