package wethinkcode.com.avaj.simulator.vehicles;

import wethinkcode.com.avaj.Flyable;
import wethinkcode.com.avaj.simulator.Logger;
import wethinkcode.com.avaj.simulator.WeatherTower;
import wethinkcode.com.avaj.weather.Coordinates;
import wethinkcode.com.avaj.weather.WeatherProvider;

public class Balloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private String namingConvention = "Balloon#" + name + "(" + getId() + "):";


    public Balloon(String name, Coordinates coordinates) {
        super(name, coordinates);
        WeatherProvider.getProvider().addLocation(coordinates);
    }

    public void updateConditions() {
        String oldWeather = this.weatherTower.getWeather(coordinates);
        WeatherProvider.getProvider().removeLocation(coordinates);
        switch (oldWeather) {
            case "SUN":
                new Logger(namingConvention + " says clear skies ahead, let's go a little higher!\n");
                coordinates.setHeight(coordinates.getHeight() + 4);
                coordinates.setLongitude(coordinates.getLongitude() + 2);
                break;
            case "RAIN":
                new Logger(namingConvention + " says rain started pouring, lowering altitude!\n");
                coordinates.setHeight(coordinates.getHeight() - 5);
                break;
            case "FOG":
                new Logger(namingConvention + " says fog showed up, we can't see anything!\n");
                coordinates.setHeight(coordinates.getHeight() - 3);
                break;
            case "SNOW":
                new Logger(namingConvention + " says there's snow inside my basket, I'm going down!\n");
                coordinates.setHeight(coordinates.getHeight() - 15);
                break;
        }
        if (coordinates.getHeight() <= 0) {
            coordinates.setHeight(0);
            new Logger(namingConvention + " has landed! It was too rough out there!\n");
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
