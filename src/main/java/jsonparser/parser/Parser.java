package jsonparser.parser;

import jsonparser.model.json.value.JsonValue;
import jsonparser.service.IParseService;
import jsonparser.util.exception.ValidationException;

public class Parser implements IParser {

    private IParseService parseService;

    public Parser(IParseService parseService) {
        this.parseService = parseService;
    }

    /**
     * Throws an exception if given string value is either null or empty
     */
    private void checkIfEmpty(String jsonString) {
        if (jsonString.isBlank()) {
            throw new ValidationException("The given string is blank! Try another one");
        }
    }

    /**
     * Counts left and right brackets of both types (square and curly), than compares counters.
     * Throws an exception if there is any mismatch
     */
    private void validateBrackets(String jsonString) {
        int squareLeft = 0;
        int squareRight = 0;
        int curlyLeft = 0;
        int curlyRight = 0;

        for (char symbol : jsonString.toCharArray()) {
            switch (symbol) {
                case '[':
                    squareLeft++;
                    break;
                case ']':
                    squareRight++;
                    break;
                case '{':
                    curlyLeft++;
                    break;
                case '}':
                    curlyRight++;
                    break;
            }
        }
        compareCounters(squareLeft, squareRight, curlyLeft, curlyRight);
    }

    private void compareCounters(int sLeft, int sRight, int cLeft, int cRight) {
        if (sLeft != sRight || cLeft != cRight) {
            throw new ValidationException("Incorrect input. Brackets mismatch. " +
                    "Found: [" + sLeft + "] squareLeft, [" + sRight + "] squareRight," +
                    " [" + cLeft + "] curlyLeft, [" + cRight + "] curlyRight");
        }
    }

    /**
     * Validates given json string. Throws an exception if validation fails.
     * Parses string and returns JsonValue
     */
    public JsonValue parseJson(String jsonString) {
        checkIfEmpty(jsonString);
        validateBrackets(jsonString);
        return (JsonValue) parseService.createJson(jsonString, 0).getObjectValue();
    }

    public void validateJsonString(){
        
    }

}

