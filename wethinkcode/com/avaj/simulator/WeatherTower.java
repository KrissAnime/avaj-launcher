package wethinkcode.com.avaj.simulator;

import wethinkcode.com.avaj.weather.Coordinates;
import wethinkcode.com.avaj.weather.WeatherProvider;

public class WeatherTower extends Tower {

    public WeatherTower(){}

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    void changeWeather(){
        conditionsChanged();
    }
}
