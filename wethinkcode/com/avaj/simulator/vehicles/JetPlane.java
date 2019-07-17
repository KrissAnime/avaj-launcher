package wethinkcode.com.avaj.simulator.vehicles;

import wethinkcode.com.avaj.Flyable;
import wethinkcode.com.avaj.simulator.Logger;
import wethinkcode.com.avaj.simulator.WeatherTower;
import wethinkcode.com.avaj.weather.Coordinates;
import wethinkcode.com.avaj.weather.WeatherProvider;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private String namingConvention = "JetPlane#" + name + "(" + getId() + "):";


    public JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
        WeatherProvider.getProvider().addLocation(coordinates);
    }

    public void updateConditions() {
        String oldWeather = this.weatherTower.getWeather(coordinates);
        WeatherProvider.getProvider().removeLocation(coordinates);
        switch (oldWeather) {
            case "SUN":
                new Logger(namingConvention + " says clear skies, going to mach 3!\n");
                coordinates.setHeight(coordinates.getHeight() + 2);
                coordinates.setLatitude(coordinates.getLatitude() + 10);
                break;
            case "RAIN":
                new Logger(namingConvention + " says it started raining, as if that's gonna stop me!\n");
                coordinates.setLatitude(coordinates.getLatitude() + 5);
                break;
            case "FOG":
                new Logger(namingConvention + " says it a fog showed up, slowing down for better visibility!\n");
                coordinates.setLatitude(coordinates.getLatitude() + 1);
                break;
            case "SNOW":
                new Logger(namingConvention + " says it started snowing, the snow is cooling down my engines!\n");
                coordinates.setHeight(coordinates.getHeight() - 7);
                break;
        }
        if (coordinates.getHeight() <= 0) {
            coordinates.setHeight(0);
            new Logger(namingConvention + " has landed! My jet needs bigger engines for this weather!\n");
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
