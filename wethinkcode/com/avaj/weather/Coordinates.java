package wethinkcode.com.avaj.weather;

import wethinkcode.com.avaj.simulator.exceptions.InvalidHeightException;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    public Coordinates(int longitude, int latitude, int height) {
        try {
            if (height <= 0) {
                throw new InvalidHeightException("");
            }
            this.height = height;
            this.latitude = latitude;
            this.longitude = longitude;
        } catch (InvalidHeightException e) {
            System.out.println("Invalid aircraft height, height needs to be at least 1");
            e.printStackTrace();
        }
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLongitude() {
        return this.longitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public int getHeight() {
        return this.height;
    }
}
