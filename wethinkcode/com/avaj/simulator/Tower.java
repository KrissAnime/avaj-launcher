package wethinkcode.com.avaj.simulator;

import wethinkcode.com.avaj.Flyable;
import wethinkcode.com.avaj.weather.WeatherProvider;

import java.util.ArrayList;
import java.util.List;

public abstract class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        if (observers.contains(flyable)) {
            return;
        }
        observers.add(flyable);
    }

    public void unregister(Flyable flyable){
        if (observers.contains(flyable)) {
            observers.remove(flyable);
        }
    }

    void conditionsChanged(){
        WeatherProvider.getProvider().changeCurrentWeather();
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }
    }
}
