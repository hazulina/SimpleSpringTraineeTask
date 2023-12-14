package org.trainee.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.trainee.exception.TraineeServletException;

public class TraineeServletExceptionTest {

    @Test
    public void testConstructorWithMessage() {
        String errorMessage = "Test error message";
        TraineeServletException exception = new TraineeServletException(errorMessage);

        Assertions.assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void testDefaultConstructor() {
        TraineeServletException exception = new TraineeServletException();

        Assertions.assertEquals("Wrong input!", exception.getMessage());
    }
}
