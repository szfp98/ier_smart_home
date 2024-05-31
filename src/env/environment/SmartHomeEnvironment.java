package environment;

import jason.asSyntax.*;
import jason.environment.*;
import model.SmartHomeModel;

import java.util.logging.Logger;

public class SmartHomeEnvironment extends Environment {
    private SmartHomeModel model;
    private final Logger logger = Logger.getLogger(SmartHomeEnvironment.class.getName());

    public static final Literal TOGGLE_LIGHT = Literal.parseLiteral("toggleLight(light)");
    public static final Literal SET_TEMPERATURE = Literal.parseLiteral("setTemperature(temperature)");
    public static final Literal OPEN_WINDOW = Literal.parseLiteral("openWindow(window)");
    public static final Literal CLOSE_WINDOW = Literal.parseLiteral("closeWindow(window)");
    public static final Literal OPEN_DOOR = Literal.parseLiteral("openDoor(door)");
    public static final Literal CLOSE_DOOR = Literal.parseLiteral("closeDoor(door)");

    @Override
    public void init(String[] args) {
        super.init(args);
        model = new SmartHomeModel();
        model.addObserver((o, arg) -> updatePercepts());
        logger.info("SmartHomeEnvironment initialized with args.");
    }
public SmartHomeModel getModel() {return model;}
    @Override
    public boolean executeAction(String agName, Structure action) {
        String act = action.getFunctor();
        try {
            switch (act) {
                case "toggleLight":
                    model.toggleDeviceState("light");
                    break;
                case "setTemperature":
                    model.setTemperature(Double.parseDouble(action.getTerm(0).toString()));
                    break;
                case "openWindow":
                    model.setDeviceState("window", true);
                    break;
                case "closeWindow":
                    model.setDeviceState("window", false);
                    break;
                case "openDoor":
                    model.setDeviceState("door", true);
                    break;
                case "closeDoor":
                    model.setDeviceState("door", false);
                    break;
                default:
                    logger.severe("Action " + act + " not supported");
                    return false;
            }
            logger.info("Executed action " + act + " by agent " + agName);
            return true;
        } catch (Exception e) {
            logger.severe("Error executing action: " + e.getMessage());
            return false;
        }
    }
    public void setRainyWeather(boolean isRainy) {
        model.setRainy(isRainy);
        updatePercepts(); // Frissítjük a perceptusokat az időjárás változása után
    }
    public void updatePercepts() {
        clearAllPercepts();
        addPercept(Literal.parseLiteral("light(" + model.getDeviceState("light") + ")"));
        addPercept(Literal.parseLiteral("temperature(" + model.getTemperature() + ")"));
        addPercept(Literal.parseLiteral("window(" + model.getDeviceState("window") + ")"));
        addPercept(Literal.parseLiteral("door(" + model.getDeviceState("door") + ")"));
        addPercept(Literal.parseLiteral("weather(" + (model.isRainy() ? "rainy" : "sunny") + ")")); // Időjárás perceptus hozzáadása

        logger.fine("Updated percepts based on the environment state.");
    }
}