package jsonparser.service.impl;

import jsonparser.model.json.JsonPair;
import jsonparser.model.json.value.JsonValue;
import jsonparser.model.json.value.impl.*;
import jsonparser.model.pack.ObjectWithPosition;
import jsonparser.service.IParseService;
import jsonparser.service.ISymbolService;
import jsonparser.service.IValueService;

public class ParseService implements IParseService {

    private ISymbolService symbolService;
    private IValueService valueService;

    public ParseService(ISymbolService symbolService, IValueService valueService) {
        this.symbolService = symbolService;
        this.valueService = valueService;
    }

    /**
     * Creates a JsonNull object.
     *
     * @return ObjectWithPosition, which contains a JsonNull object and a position, where reading has stopped.
     */
    private ObjectWithPosition createJsonNull(int startPosition) {
        JsonNull jsonNull = new JsonNull();
        startPosition += 3;
        return new ObjectWithPosition(jsonNull, startPosition);
    }

    /**
     * Creates a JsonNumber object.
     *
     * @return ObjectWithPosition, which contains a JsonNumber object and a position, where reading has stopped.
     */
    private ObjectWithPosition createJsonNumber(String jsonString, int startPosition) {
        ObjectWithPosition positionValuePackage = valueService.createNumericValue(jsonString, startPosition);
        JsonNumber jsonNumber = (JsonNumber) positionValuePackage.getObjectValue();
        startPosition = positionValuePackage.getPosition();
        return new ObjectWithPosition(jsonNumber, startPosition);
    }

    /**
     * Creates a JsonString object.
     *
     * @return ObjectWithPosition, which contains a JsonString object and a position, where reading has stopped.
     */
    private ObjectWithPosition createJsonString(String jsonString, int startPosition) {
        ObjectWithPosition stringValuePackage = valueService.createStringValue(jsonString, startPosition);
        JsonString jsonValue = (JsonString) stringValuePackage.getObjectValue();
        startPosition = stringValuePackage.getPosition();
        return new ObjectWithPosition(jsonValue, startPosition);
    }

    /**
     * Creates a JsonArray object.
     * While ']' symbol is not found keeps adding values to array's inner list
     *
     * @return ObjectWithPosition, which contains a JsonArray object and a position, where reading has stopped.
     */
    private ObjectWithPosition createJsonArray(String jsonString, char currentChar, int startPosition) {
        JsonArray jsonArray = new JsonArray();
        while (currentChar != ']' && symbolService.getNextChar(jsonString, startPosition) != ']') {
            ObjectWithPosition receivedValue = createJson(jsonString, startPosition + 1);
            jsonArray.addElement((JsonValue) receivedValue.getObjectValue());
            startPosition = receivedValue.getPosition() + 1; //was +2
            currentChar = jsonString.charAt(startPosition);
        }
        if (currentChar != ']') {
            String buf = jsonString.substring(startPosition);
            startPosition += buf.indexOf(']');
        }
        return new ObjectWithPosition(jsonArray, startPosition);
    }

    /**
     * Creates a JsonObject object.
     * While '}' symbol is not found keeps adding pairs to object's inner list
     *
     * @return ObjectWithPosition, which contains a JsonObject object and a position, where reading has stopped.
     */
    private ObjectWithPosition createJsonObject(String jsonString, char currentChar, int startPosition) {
        JsonObject jsonObject = new JsonObject();
        while (currentChar != '}' && symbolService.getNextChar(jsonString, startPosition) != '}') {
            ObjectWithPosition receivedPair = valueService.createPair(jsonString, startPosition);
            jsonObject.addField((JsonPair) receivedPair.getObjectValue());
            startPosition = receivedPair.getPosition() + 1; //was +2
            currentChar = jsonString.charAt(startPosition);
        }
        if (currentChar != '}') {
            String buf = jsonString.substring(startPosition);
            startPosition += buf.indexOf('}');
        }
        return new ObjectWithPosition(jsonObject, startPosition);
    }

    /**
     * Reads given string char by char, defines current's value type. Creates a JsonValue and returns it.
     *
     * @return ObjectWithPosition, which contains a JsonValue object and a position, where reading has stopped.
     */
    public ObjectWithPosition createJson(String jsonString, int startPosition) {
        char currentChar = jsonString.charAt(startPosition);
        while (true) {
            if (currentChar == '{') {
                return createJsonObject(jsonString, currentChar, startPosition);
            }

            if (currentChar == '[') {
                return createJsonArray(jsonString, currentChar, startPosition);
            }

            if (currentChar == '"') {
                return createJsonString(jsonString, startPosition);
            }

            if (symbolService.isNumber(currentChar)) {
                return createJsonNumber(jsonString, startPosition);
            }

            if (symbolService.isNull(currentChar)) {
                return createJsonNull(startPosition);
            }

            if (symbolService.isBoolean(currentChar)) {
                return valueService.createBooleanValue(jsonString, startPosition);
            }
            startPosition++;
            currentChar = jsonString.charAt(startPosition);
        }
    }

}
