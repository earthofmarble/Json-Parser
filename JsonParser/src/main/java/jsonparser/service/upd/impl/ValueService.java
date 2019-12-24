package jsonparser.service.upd.impl;

import jsonparser.model.json.JPair;
import jsonparser.model.json.value.JValue;
import jsonparser.model.json.value.impl.JBoolean;
import jsonparser.model.json.value.impl.JNumber;
import jsonparser.model.json.value.impl.JString;
import jsonparser.model.pack.ObjectIntPackage;
import jsonparser.model.pack.PairPositionPackage;
import jsonparser.model.pack.PositionValuePackage;
import jsonparser.model.pack.StringPositionPackage;
import jsonparser.service.upd.ISymbolService;
import jsonparser.service.upd.IValueService;

import java.io.Serializable;

public class ValueService implements IValueService {

    private ISymbolService symbolService = new SymbolService();

    public PairPositionPackage createPair(String jsonString, int startPosition) {
        ParseService parseService = new ParseService();
        JPair pair = new JPair();
        StringPositionPackage stringPositionPackage = getFieldName(jsonString, startPosition);
        pair.setName(stringPositionPackage.getString());
        startPosition = stringPositionPackage.getPosition();
        PositionValuePackage positionValuePackage = parseService.createJson(jsonString, startPosition+1);
        pair.setValue(positionValuePackage.getValue());
        return new PairPositionPackage(pair, positionValuePackage.getPosition());
    }

    private StringPositionPackage getFieldName(String jsonString, int position){
        String fieldName = jsonString.substring(position);
        int firstQuotPosition = fieldName.indexOf("\"")+position; //was +1
        fieldName = fieldName.substring(fieldName.indexOf("\"")+1);
        fieldName = fieldName.substring(0, fieldName.indexOf("\""));
//        return new StringPositionPackage(fieldName,
//                jsonString.indexOf("\""+fieldName+"\"")+fieldName.length()+2);
        return new StringPositionPackage(fieldName,
                firstQuotPosition+fieldName.length()+2); //mb 1
    }

    public PositionValuePackage createArrayValue(String jsonString, int startPosition) {
        return null;
    }

    public PositionValuePackage createStringValue(String jsonString, int startPosition) {
        int stopPos = startPosition;
        while (symbolService.getNextChar(jsonString, stopPos)!='"'){
            stopPos++;
        }
        String value = jsonString.substring(startPosition+1, stopPos+1); //mb +2
        JValue jString = new JString(value);
        return new PositionValuePackage(stopPos, jString);
    }

    public PositionValuePackage createNumericValue(String jsonString, int startPosition) {
        ObjectIntPackage numericPackage = getNumberValue(jsonString, startPosition);
        JValue jNumber = new JNumber((Number) numericPackage.getObjectValue());
        return new PositionValuePackage(numericPackage.getIntValue(), jNumber);
    }

    public PositionValuePackage createBooleanValue(String jsonString, int startPosition) {
        JValue jBoolean;
        String croppedString = jsonString.substring(startPosition);
        if (croppedString.startsWith("true")){
            jBoolean = new JBoolean(true);
            return new PositionValuePackage(startPosition+3, jBoolean);
        }
        if (croppedString.startsWith("false")){
            jBoolean = new JBoolean(false);
            return new PositionValuePackage(startPosition+4, jBoolean);
        }
        throw new RuntimeException("JSON syntax error. Expected true or false parameter. Found: ["+croppedString+"]");
    }

    public PositionValuePackage createNullValue(String jsonString, int startPosition) {
        return null;
    }


    public ObjectIntPackage getNumberValue(String jsonString, int startPos) {
        startPos = findStartPosition(jsonString, startPos);
        ObjectIntPackage receivedPackage = findStopPosition(startPos, jsonString);
        int stopPos = receivedPackage.getIntValue();
        boolean commaFound = (boolean) receivedPackage.getObjectValue();
        if (commaFound) {
            return new ObjectIntPackage(Double.valueOf(jsonString.substring(startPos, stopPos + 1)),
                    stopPos); //was +1
        }
        return new ObjectIntPackage(Integer.valueOf(jsonString.substring(startPos, stopPos + 1)),
                stopPos); //was +1
    }

    private ObjectIntPackage findStopPosition(int startPos, String jsonString){
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
        return new ObjectIntPackage(commaFound, stopPos);
    }

    private int findStartPosition(String jsonString, int startPos){
        char[] jsonChars = jsonString.toCharArray();
        for (int charIndex = startPos; charIndex<jsonChars.length; charIndex++) {
            if (symbolService.isNumber(jsonChars[charIndex])) {
                startPos = charIndex;
                break;
            }
        }
        return startPos;
    }


}
