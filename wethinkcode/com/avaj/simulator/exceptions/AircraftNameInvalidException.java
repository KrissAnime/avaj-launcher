package wethinkcode.com.avaj.simulator.exceptions;

public class AircraftNameInvalidException extends Exception {
    public AircraftNameInvalidException(String message) {
        super(message);
    }

    public String getMessage() {
        return super.getMessage();
    }
}
