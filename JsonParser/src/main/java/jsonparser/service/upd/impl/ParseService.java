package jsonparser.service.upd.impl;

import jsonparser.model.json.value.JValue;
import jsonparser.model.json.value.impl.JNull;
import jsonparser.model.json.value.impl.JObject;
import jsonparser.model.pack.PairPositionPackage;
import jsonparser.model.pack.PositionValuePackage;
import jsonparser.service.upd.ISymbolService;
import jsonparser.service.upd.IValueService;

public class ParseService {

    ISymbolService symbolService = new SymbolService();
    IValueService valueService = new ValueService();

    public PositionValuePackage createJson(String jsonString, int startPosition) {
        JValue jValue;
        char currentChar = jsonString.charAt(startPosition);
        while (true) {
            if (currentChar == '{') {
                jValue = new JObject();
                while (currentChar != '}' && symbolService.getNextChar(jsonString, startPosition) != '}') {
                    PairPositionPackage receivedPair = valueService.createPair(jsonString, startPosition);
                    ((JObject) jValue).addField(receivedPair.getjPair());
                    startPosition = receivedPair.getPosition()+1; //was +2
                    currentChar = jsonString.charAt(startPosition);
                }
                if (currentChar!='}'){
                    String buf = jsonString.substring(startPosition);
                    startPosition += buf.indexOf('}');
                }
                return new PositionValuePackage(startPosition, jValue);
            }

            if (currentChar == '"') {
                PositionValuePackage stringValuePackage = valueService.createStringValue(jsonString, startPosition);
                jValue = stringValuePackage.getValue();
                startPosition = stringValuePackage.getPosition();
                currentChar = jsonString.charAt(startPosition);
                return new PositionValuePackage(startPosition, jValue);
            }

            if (symbolService.isNumber(currentChar)) {
                PositionValuePackage positionValuePackage = valueService.createNumericValue(jsonString, startPosition);
                jValue = positionValuePackage.getValue();
                startPosition = positionValuePackage.getPosition();
                currentChar = jsonString.charAt(startPosition);
                return new PositionValuePackage(startPosition, jValue);
            }

            if (symbolService.isNull(currentChar)) {
                jValue = new JNull();
                startPosition += 3;
                return new PositionValuePackage(startPosition, jValue);
            }

            if (symbolService.isBoolean(currentChar)){
                return valueService.createBooleanValue(jsonString, startPosition);
            }
            startPosition++;
            currentChar = jsonString.charAt(startPosition);
        }
    }

}
