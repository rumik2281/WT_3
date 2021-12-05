package by.bsuir.lab3.server.controller;

import by.bsuir.lab3.server.controller.impl.ControllerImpl;

public class ControllerFactory {
    private static final Controller controller = new ControllerImpl();

    public static Controller getController() {
        return controller;
    }

    private ControllerFactory() {
    }
}
