package wethinkcode.com.avaj.simulator.vehicles;

import wethinkcode.com.avaj.Flyable;
import wethinkcode.com.avaj.simulator.Logger;
import wethinkcode.com.avaj.simulator.WeatherTower;
import wethinkcode.com.avaj.weather.Coordinates;
import wethinkcode.com.avaj.weather.WeatherProvider;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private String namingConvention = "Helicopter#" + name + "(" + getId() + "):";

    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
        WeatherProvider.getProvider().addLocation(coordinates);
    }

    public void updateConditions() {
        String oldWeather = this.weatherTower.getWeather(coordinates);
        WeatherProvider.getProvider().removeLocation(coordinates);
        switch (oldWeather) {
            case "SUN":
                new Logger(namingConvention + " says clear skies for apache's!\n");
                coordinates.setHeight(coordinates.getHeight() + 2);
                coordinates.setLongitude(coordinates.getLongitude() + 10);
                break;
            case "RAIN":
                new Logger(namingConvention + " says it started raining here!\n");
                coordinates.setLongitude(coordinates.getLongitude() + 5);
                break;
            case "FOG":
                new Logger(namingConvention + " says I can't see anything through this fog, switching to radar.\n");
                coordinates.setLongitude(coordinates.getLongitude() + 1);
                break;
            case "SNOW":
                new Logger(namingConvention + " says snow is building on my rotors, I'm going down!\n");
                coordinates.setHeight(coordinates.getHeight() - 12);
                break;
        }

        if (coordinates.getHeight() <= 0) {
            coordinates.setHeight(0);
            new Logger(namingConvention + " has landed! Shutting down rotors.\n");
            this.weatherTower.unregister(this);
            new Logger(namingConvention + " has been unregistered from the tower!\n");
            return;
        } else if (coordinates.getHeight() > 100) {
            coordinates.setHeight(100);
            new Logger(namingConvention + " can't go any higher!\n");
        }
        WeatherProvider.getProvider().addLocation(coordinates);
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        WeatherProvider.getProvider().generateWeather(coordinates);
        new Logger("Tower says: " + namingConvention  + " registered to tower.\n");
    }
}
