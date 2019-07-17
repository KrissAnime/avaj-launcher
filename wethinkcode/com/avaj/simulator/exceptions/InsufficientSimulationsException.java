package wethinkcode.com.avaj.simulator.exceptions;

public class InsufficientSimulationsException extends Exception {
    public InsufficientSimulationsException(String message) {
        super(message);
    }

    public String getMessage() {
        return super.getMessage();
    }
}