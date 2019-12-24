package jsonparser.service.upd.impl;

import jsonparser.model.json.JsonPair;
import jsonparser.model.json.value.JsonValue;
import jsonparser.model.json.value.impl.JsonBoolean;
import jsonparser.model.json.value.impl.JsonNumber;
import jsonparser.model.json.value.impl.JsonString;
import jsonparser.model.pack.ObjectWithPosition;
import jsonparser.service.upd.ISymbolService;
import jsonparser.service.upd.IValueService;

public class ValueService implements IValueService {

    private ISymbolService symbolService;

    public ValueService(ISymbolService symbolService) {
        this.symbolService = symbolService;
    }

    /**
     * Truncates given string on startPosition, saves index of a first quot symbol,
     * than saves value inside quot symbols to a pairName variable.
     *
     * @return returns an ObjectWithPosition, which contains a pairName and a position of last quot symbol in a given string
     */
    private ObjectWithPosition getPairName(String jsonString, int startPosition) {
        String pairName = jsonString.substring(startPosition);
        int firstQuotPosition = pairName.indexOf("\"") + startPosition; //was +1
        pairName = pairName.substring(pairName.indexOf("\"") + 1);
        pairName = pairName.substring(0, pairName.indexOf("\""));
        return new ObjectWithPosition(pairName, firstQuotPosition + pairName.length() + 2);
    }

    /**
     * @return returns an index of a first digit in given string
     */
    private int findStartPosition(String jsonString, int startPos) {
        char[] jsonChars = jsonString.toCharArray();
        for (int charIndex = startPos; charIndex < jsonChars.length; charIndex++) {
            if (symbolService.isNumber(jsonChars[charIndex])) {
                startPos = charIndex;
                break;
            }
        }
        return startPos;
    }

    /**
     * @return returns an index of a last digit in given string
     */
    private ObjectWithPosition findStopPosition(int startPos, String jsonString) {
        int stopPos = 0;
        boolean commaFound = false;
        for (int charIndex = startPos; charIndex < jsonString.length(); charIndex++) {
            if (!symbolService.isNumber(jsonString.charAt(charIndex))) {
                break;
            }
            if (jsonString.charAt(charIndex) == '.') {
                commaFound = true;
            }
            stopPos = charIndex;
        }
        return new ObjectWithPosition(commaFound, stopPos);
    }

    /**
     * Parses string from given startPosition.
     * Receives a pair name from a {@link #getPairName(String, int) getPairName} method,
     * then receives a pair value from a {@link ParseService#createJson(String, int) createJson} method
     *
     * @return returns ObjectWithPosition, which contains a JsonPair object and a position, where reading has stopped.
     */
    public ObjectWithPosition createPair(String jsonString, int startPosition) {
        ParseService parseService = new ParseService(symbolService, new ValueService(symbolService));
        ObjectWithPosition pairNamePackage = getPairName(jsonString, startPosition);
        startPosition = pairNamePackage.getPosition();
        ObjectWithPosition pairValuePackage = parseService.createJson(jsonString, startPosition + 1);
        JsonPair pair = new JsonPair((String) pairNamePackage.getObjectValue(),
                (JsonValue) pairValuePackage.getObjectValue());
        return new ObjectWithPosition(pair, pairValuePackage.getPosition());
    }

    /**
     * Finds an index where a string value ends. Truncates string on startPosition and stopPosition, saves result to a value
     * Creates a JsonString object with saved string as a value
     *
     * @return returns an ObjectWithPosition, which contains a JsonString object and a position, where reading has stopped.
     */
    public ObjectWithPosition createStringValue(String jsonString, int startPosition) {
        int stopPos = startPosition;
        while (symbolService.getNextChar(jsonString, stopPos) != '"') {
            stopPos++;
        }
        String value = jsonString.substring(startPosition + 1, stopPos + 1);
        JsonValue jString = new JsonString(value);
        return new ObjectWithPosition(jString, stopPos + 1);
    }

    /**
     * Receives a numeric value from a {@link #parseNumberValue(String, int) getNumberValue} method,
     * Creates a JsonNumber object with received number as a value
     *
     * @return returns an ObjectWithPosition, which contains a JsonNumber object and a position, where reading has stopped.
     */
    public ObjectWithPosition createNumericValue(String jsonString, int startPosition) {
        ObjectWithPosition numericPackage = parseNumberValue(jsonString, startPosition);
        JsonValue jNumber = new JsonNumber((Number) numericPackage.getObjectValue());
        return new ObjectWithPosition(jNumber, numericPackage.getPosition());
    }

    /**
     * Truncates given string on start position. If cropped string starts with "true",
     * creates a JsonBoolean object with value true, and vice versa
     *
     * @return returns an ObjectWithPosition, which contains a JsonBoolean object
     *          and a start position increased by 3 or 4, depending on if value equals true or false
     */
    public ObjectWithPosition createBooleanValue(String jsonString, int startPosition) {
        JsonValue jBoolean;
        String croppedString = jsonString.substring(startPosition);
        if (croppedString.startsWith("true")) {
            jBoolean = new JsonBoolean(true);
            return new ObjectWithPosition(jBoolean, startPosition + 3);
        }
        if (croppedString.startsWith("false")) {
            jBoolean = new JsonBoolean(false);
            return new ObjectWithPosition(jBoolean, startPosition + 4);
        }
        throw new RuntimeException("JSON syntax error. Expected true or false parameter. Found: [" + croppedString + "]");
    }

    /**
     * Receives first digit index from a {@link #findStartPosition(String, int) findStartPosition} method,
     * receives last digit index from a {@link #findStopPosition(int, String) findStopPosition} method,
     *
     * @return returns an ObjectWithPosition, which contains an integer or double value depending on
     *          if [.] symbol was found or not, and a position, where reading has stopped.
     */
    public ObjectWithPosition parseNumberValue(String jsonString, int startPos) {
        startPos = findStartPosition(jsonString, startPos);
        ObjectWithPosition receivedPackage = findStopPosition(startPos, jsonString);
        int stopPos = receivedPackage.getPosition();
        boolean commaFound = (boolean) receivedPackage.getObjectValue();
        if (commaFound) {
            return new ObjectWithPosition(Double.valueOf(jsonString.substring(startPos, stopPos + 1)), stopPos);
        }
        return new ObjectWithPosition(Integer.valueOf(jsonString.substring(startPos, stopPos + 1)), stopPos);
    }



}
