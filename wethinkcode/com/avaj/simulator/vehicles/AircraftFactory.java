package wethinkcode.com.avaj.simulator.vehicles;

import wethinkcode.com.avaj.Flyable;
import wethinkcode.com.avaj.simulator.exceptions.AircraftNameInvalidException;
import wethinkcode.com.avaj.weather.Coordinates;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class AircraftFactory {
    private static String regex = "^[a-zA-Z0-9]+$";
    private static Pattern pattern = Pattern.compile(regex);

    public AircraftFactory(){}


    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        try {
            Matcher matcher = pattern.matcher(name);
            if (!matcher.matches()) {
                throw new AircraftNameInvalidException("Aircraft name takes alphanumeric characters only");
            }
            Coordinates coordinates = new Coordinates(longitude, latitude, height);
            switch (type) {
                case "Balloon":
                    return new Balloon(name, coordinates);
                case "Helicopter":
                    return new Helicopter(name, coordinates);
                case "JetPlane":
                    return new JetPlane(name, coordinates);
            }
            return null;
        } catch (AircraftNameInvalidException e) {
            System.out.println("An invalid aircraft name was given");
            e.printStackTrace();
        }
        return null;
    }
}
