package wethinkcode.com.avaj;


import wethinkcode.com.avaj.simulator.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
}
