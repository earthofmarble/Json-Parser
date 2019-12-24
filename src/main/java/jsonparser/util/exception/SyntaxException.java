package jsonparser.util.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyntaxException extends RuntimeException {

    private static Logger logger = LoggerFactory.getLogger(SyntaxException.class);

    public SyntaxException(String message) {
        super(message);
        logger.info(message);
    }
}