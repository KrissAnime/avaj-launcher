package wethinkcode.com.avaj.simulator.vehicles;

import wethinkcode.com.avaj.weather.Coordinates;

public abstract class Aircraft {
    protected long      id;
    protected String    name;
    protected Coordinates coordinates;

    private static long idCounter = 0;

    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        setId();
    }

    public void setId() {
        this.id = nextId();
    }

    private long nextId(){
        return ++idCounter;
    }

    public long getId() {
        return this.id;
    }
}
