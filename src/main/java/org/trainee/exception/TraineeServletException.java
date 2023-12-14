package org.trainee.exception;

public class TraineeServletException extends RuntimeException {

    public TraineeServletException(String message) {
        super(message);
    }

    public TraineeServletException() {
        super("Wrong input!");
    }
}
