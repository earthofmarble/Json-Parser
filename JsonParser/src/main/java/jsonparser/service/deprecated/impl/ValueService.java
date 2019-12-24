//package jsonparser.service.deprecated.impl;
//
//import jsonparser.enumeration.BracketType;
//import jsonparser.enumeration.ValueType;
//
//import jsonparser.model.deprecated.Package;
//import jsonparser.model.deprecated.PositionPackage;
//import jsonparser.model.json.JPair;
//import jsonparser.model.json.value.JValue;
//import jsonparser.model.json.value.impl.*;
//import jsonparser.service.deprecated.IJsonFactory;
//import jsonparser.service.deprecated.ISymbolService;
//import jsonparser.service.deprecated.IValueService;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//@Deprecated
//public class ValueService implements IValueService {
//
////    private ISymbolService symbolService = new SymbolService();
//    private IJsonFactory jsonFactory = new JsonFactory();
//
//    /**
//     * fills field's value based on given:
//     * @param type
//     */
//    private Package getInnerObjectValue(JPair pair, String jsonString, ValueType type) {
//        switch (type) {
//            case OBJECT:
//                return objectLogic(jsonString, pair);
//            case ARRAY:
//                return arrayLogic(jsonString, pair);
//            case BOOLEAN:
//                return booleanLogic(jsonString, pair);
//            case NULL:
//                return nullLogic(jsonString, pair);
//            case NUMBER:
//                return numberLogic(jsonString, pair);
//            case STRING:
//                return stringLogic(jsonString, pair);
//        }
//        return new Package(null, jsonString);
//    }
//
//    /**
//     * reads string value from:
//     * @param jsonString sets value to:
//     * @param pair
//     * @return returns given json + updatedString
//     */
//    private Package stringLogic(String jsonString, JPair pair) {
//        String stringValue = getQuotedValue(jsonString);
//        if (stringValue==null){
//            throw new RuntimeException("Incorrect input. Wrong [\"] symbol placement");
//        }
//        JString jString = (JString) pair.getValue();
//        jString.setValue(stringValue);
//        jsonString = jsonString.substring(jsonString.indexOf(stringValue) + stringValue.length() + 2);
//        return new Package(pair, jsonString);
//    }
//
//    /**
//     * reads numeric value from:
//     * @param jsonString sets value to:
//     * @param pair
//     * @return returns given json + updatedString
//     */
//    private Package numberLogic(String jsonString, JPair pair) {
//        PositionPackage receivedPackage = getNumberValue(jsonString);
//        Serializable numericValue = (Serializable) receivedPackage.getValue();
//        JNumber jNumber = (JNumber) pair.getValue();
//        jNumber.setValue(numericValue);
//        return new Package(pair, jsonString.substring(receivedPackage.getPosition()));
//    }
//
//    private PositionPackage findStopPosition(int startPos, String jsonString){
//        int stopPos = 0;
//        boolean commaFound = false;
//        for (int charIndex = startPos; charIndex < jsonString.length(); charIndex++) {
//            if (!symbolService.isNumber(jsonString.charAt(charIndex))) {
//                break;
//            }
//            if (jsonString.charAt(charIndex) == '.') {
//                commaFound = true;
//            }
//            stopPos = charIndex;
//        }
//        return new PositionPackage(commaFound, stopPos);
//    }
//
//    private int findStartPosition(String jsonString){
//        int startPos = 0;
//        for (char character : jsonString.toCharArray()) {
//            if (symbolService.isNumber(character)) {
//                startPos = jsonString.indexOf(character);
//                break;
//            }
//        }
//        return startPos;
//    }
//
//    /**
//     * truncates string
//     * @return returns given json + updatedString
//     */
//    private Package nullLogic(String jsonString, JPair pair) {
//        int nIndex = jsonString.indexOf('n');
//        return new Package(pair, jsonString.substring(nIndex+4));
//    }
//
//    /**
//     * reads boolean value from:
//     * @param jsonString sets value to:
//     * @param pair
//     * @return returns given json + updatedString
//     */
//    private Package booleanLogic(String jsonString, JPair pair) {
//        Character character = symbolService.getNextChar(jsonString, 1);
//        if (character == null) {
//            throw new RuntimeException("Given string is empty. [true] or [false] expected");
//        }
//        JBoolean jBoolean;
//        switch (character) {
//            case 't':
//                jBoolean = (JBoolean) pair.getValue();
//                jBoolean.setValue(true);
//                return new Package(pair,
//                        jsonString.substring(jsonString.indexOf('t'), jsonString.indexOf('e') + 1));
//            case 'f':
//                jBoolean = (JBoolean) pair.getValue();
//                jBoolean.setValue(false);
//                return new Package(pair,
//                        jsonString.substring(jsonString.indexOf('f'), jsonString.indexOf('e') + 1));
//            default:
//                throw new RuntimeException("Wrong symbol [" + character + "]. [true] or [false] expected");
//        }
//    }
//
//    private Package arrayLogic(String jsonString, JPair pair) {
//        String bracketsValue = getBracketsValue(jsonString, BracketType.SQUARE);
//        int finalSubstringIndex = jsonString.indexOf(bracketsValue) + bracketsValue.length();
//        while (true) {
//            ValueType innerValueType = symbolService.getInnerType(bracketsValue, 0);
//            if (innerValueType.equals(ValueType.NONE)) {
//                break;
//            }
//            JValue newJValue = jsonFactory.createValue(innerValueType);
//            JPair nullNamePair = new JPair();
//            nullNamePair.setValue(newJValue);
//            Package receivedPackage = getInnerObjectValue(nullNamePair, bracketsValue, innerValueType);
//            JArray jArray = (JArray) pair.getValue();
//            jArray.getElements().add(receivedPackage.getPair().getValue());
//            bracketsValue = receivedPackage.getUpdatedString();
//        }
//        return new Package(pair, jsonString.substring(finalSubstringIndex));
//    }
//
//    /**
//     * gets string value of curly brackets, while there are any elements inside brackets adds them to:
//     * @param pair 's elements list
//     * @return returns updated string + jsonObject
//     */
//    private Package objectLogic(String jsonString, JPair pair) {
//        String bracketsValue = getBracketsValue(jsonString, BracketType.CURLY);
//        int finalSubstringIndex = jsonString.indexOf(bracketsValue) + bracketsValue.length();
//        while (true) {
//            Package pack = createField(bracketsValue);
//            if (pack == null) {
//                break;
//            }
//            JObject jObject = (JObject) pair.getValue();
//            jObject.getFields().add(pack.getPair());
//            bracketsValue = pack.getUpdatedString();
//        }
//        return new Package(pair, jsonString.substring(finalSubstringIndex));
//    }
//
//    /**
//     * counts left and right brackets, when right counter becomes equal to left one
//     * truncates given jsonString and
//     * @return returns value between the first and the last bracket
//     */
//    private String getBracketsValue(String jsonString, BracketType bracketType) {
//        int leftBracketCounter = 0;
//        int rightBracketCounter = 0;
//
//        char leftBracket;
//        char rightBracket;
//
//        switch (bracketType){
//            case CURLY:
//                leftBracket = '{';
//                rightBracket = '}';
//                break;
//            case SQUARE:
//                leftBracket = '[';
//                rightBracket = ']';
//                break;
//            default:
//                throw new RuntimeException("Bracket type might not have been initialized. " +
//                        "BracketType ["+bracketType+"], [CURLY] or [SQUARE] expected");
//        }
//
//        List<Integer> leftBracketsPos = new ArrayList<>();
//        List<Integer> rightBracketsPos = new ArrayList<>();
//
//        for (int charIndex = 0; charIndex < jsonString.length(); charIndex++) {
//            char currentChar = jsonString.charAt(charIndex);
//            if (currentChar == leftBracket) {
//                leftBracketsPos.add(charIndex);
//                leftBracketCounter++;
//            }
//            if (currentChar == rightBracket && leftBracketCounter>0) {
//                rightBracketsPos.add(charIndex);
//                rightBracketCounter++;
//                if (compareCounters(leftBracketCounter, rightBracketCounter)) {
//                    sortLists(leftBracketsPos, rightBracketsPos);
//                    return jsonString.substring(leftBracketsPos.get(0) + 1,
//                            rightBracketsPos.get(rightBracketsPos.size() - 1));
//                }
//            }
//        }
//        throw new RuntimeException("No brackets found");
//    }
//
//    private void sortLists(List<Integer> left, List<Integer> right) {
//        left.sort(Integer::compareTo);
//        right.sort(Integer::compareTo);
//    }
//
//    private boolean compareCounters(int left, int right) {
//        return left == right;
//    }
//
//    /**
//     * reads field's name, creates an object, truncates a string
//     * @return returns created object and updated string
//     */
//    public Package createField(String jsonString) {
//        String fieldName = getQuotedValue(jsonString);
//        if (fieldName == null){
//            return null;
//        }
//        int jsonQuotIndex = jsonString.indexOf(fieldName) + fieldName.length() + 1;
//        jsonString = jsonString.substring(jsonQuotIndex);
//        ValueType innerValueType = symbolService.getInnerType(jsonString, 0);
//        if (innerValueType.equals(ValueType.NONE)) {
//            return null;
//        }
//        JPair pair = new JPair();
//        JValue newJValue = jsonFactory.createValue(innerValueType);
//        pair.setName(fieldName);
//        pair.setValue(newJValue);
//        Package receivedPackage = getInnerObjectValue(pair, jsonString, innerValueType);
//        return new Package(receivedPackage.getPair(), receivedPackage.getUpdatedString());
//    }
//
//    /**
//     * finds index of first digit, and of the last one
//     * truncates given:
//     * @param jsonString
//     * @return returns integer or double value based on whether a [.] symbol was found
//     */
//    public PositionPackage getNumberValue(String jsonString) {
//        int startPos = findStartPosition(jsonString);
//        PositionPackage receivedPackage = findStopPosition(startPos, jsonString);
//        int stopPos = receivedPackage.getPosition();
//        boolean commaFound = (boolean) receivedPackage.getValue();
//        if (commaFound) {
//            return new PositionPackage(Double.valueOf(jsonString.substring(startPos, stopPos + 1)),
//                    stopPos + 1);
//        }
//        return new PositionPackage(Integer.valueOf(jsonString.substring(startPos, stopPos + 1)),
//                stopPos + 1);
//    }
//
//    /**
//     * truncates:
//     * @param jsonString in " character entry points
//     * @return fieldValue
//     */
//    public String getQuotedValue(String jsonString){
//        String fieldName = jsonString.substring(jsonString.indexOf("\"") + 1);
//        int fieldNameQuotIndex = fieldName.indexOf("\"");
//        if (fieldNameQuotIndex == -1) {
//            return null;
//        }
//        return fieldName.substring(0, fieldNameQuotIndex);
//    }
//
//
//}
