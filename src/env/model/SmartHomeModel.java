package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Logger;

public class SmartHomeModel extends Observable {
    private Map<String, Boolean> deviceStates;
    private double temperature;
    private int time;
    private boolean rainyWeather;
    private final Logger logger = Logger.getLogger(SmartHomeModel.class.getName());

    public SmartHomeModel() {
        deviceStates = new HashMap<>();
        initializeDeviceStates();
    }

    private void initializeDeviceStates() {
        deviceStates.put("light", false);
        deviceStates.put("airConditioner", false);
        deviceStates.put("curtains", true);
        deviceStates.put("door", false);
        deviceStates.put("window", false);
        temperature = 22.0;
        time = 0;
        logger.info("Device states initialized.");
    }

    public boolean getDeviceState(String device) {
        return deviceStates.getOrDefault(device, false);
    }

    public void setDeviceState(String device, boolean state) {
        deviceStates.put(device, state);
        setChanged();
        notifyObservers(device);
        logger.info("Device state updated: " + device + " to " + state);
    }

    public void toggleDeviceState(String device) {
        boolean newState = !getDeviceState(device);
        setDeviceState(device, newState);
        logger.info("Toggled device state: " + device + " to " + newState);
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        setChanged();
        notifyObservers("temperature");
        logger.info("Temperature set to " + temperature);
    }
    public boolean isRainy() {
        return rainyWeather;
    }

    public void setRainy(boolean rainy) {
        this.rainyWeather = rainy;
        setChanged();
        notifyObservers("weather");
        logger.info("Weather set to " + (rainy ? "rainy" : "sunny"));
    }

    public int getTime() {
        return time;
    }

   /* public void setDaytime(boolean isDaytime) {
        this.isDaytime = isDaytime;
        setChanged();
        notifyObservers("daytime");
        logger.info("Daytime set to " + isDaytime);
    }*/
}