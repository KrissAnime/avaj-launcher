package wethinkcode.com.avaj.weather;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {"SUN", "RAIN", "FOG", "SNOW"};
    private HashMap<String, String> WeatherMap = new HashMap<>();

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    private WeatherProvider() {
    }

    public String getCurrentWeather(Coordinates coordinates) {
        String location = String.valueOf(coordinates.getHeight()) + " " + String.valueOf(coordinates.getLatitude()) + " " + String.valueOf(coordinates.getLongitude());
        return WeatherMap.get(location);
    }

    public void changeCurrentWeather() {
        for (Map.Entry<String, String> entry : WeatherMap.entrySet()) {
            entry.setValue(setRandomWeather(entry.getValue()));
        }
    }

    private String setRandomWeather(String currentWeather) {
        int randomIndex = ThreadLocalRandom.current().nextInt(0, weather.length);
        return weather[randomIndex];
    }

    public void generateWeather(Coordinates coordinates) {
        String location = String.valueOf(coordinates.getHeight()) + " " + String.valueOf(coordinates.getLatitude()) + " " + String.valueOf(coordinates.getLongitude());
        WeatherMap.put(location, setRandomWeather(""));
    }

    public void removeLocation(Coordinates coordinates) {
        WeatherMap.remove(String.valueOf(coordinates.getHeight()) + " " + String.valueOf(coordinates.getLatitude()) + " " + String.valueOf(coordinates.getLongitude()));

    }

    public void addLocation(Coordinates coordinates) {
        WeatherMap.put(String.valueOf(coordinates.getHeight()) + " " + String.valueOf(coordinates.getLatitude()) + " " + String.valueOf(coordinates.getLongitude()), setRandomWeather(""));
    }
}
