package jsonparser.util.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SymbolMissingException extends RuntimeException {

    private static Logger logger = LoggerFactory.getLogger(SymbolMissingException.class);

    public SymbolMissingException(String message) {
        super(message);
        logger.info(message);
    }
}