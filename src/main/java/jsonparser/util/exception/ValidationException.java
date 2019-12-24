package jsonparser.util.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationException extends RuntimeException {

    private static Logger logger = LoggerFactory.getLogger(ValidationException.class);

    public ValidationException(String message) {
        super(message);
        logger.info(message);
    }
}