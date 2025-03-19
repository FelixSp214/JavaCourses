package prv.felix.javacourses.console;

import prv.felix.javacourses.controller.Controller;
import prv.felix.javacourses.interfaces.IClient;

public class ConsoleUI implements IClient {

    private Controller controller;

    public ConsoleUI(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void show() {
        try {
            controller.getAllJavaCourses();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
