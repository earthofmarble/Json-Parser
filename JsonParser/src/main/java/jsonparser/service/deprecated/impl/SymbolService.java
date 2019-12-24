//package jsonparser.service.deprecated.impl;
//
//import jsonparser.enumeration.ValueType;
//import jsonparser.model.json.Json;
//import jsonparser.model.json.value.JValue;
//import jsonparser.model.json.value.impl.JNumber;
//import jsonparser.model.json.value.impl.JString;
//import jsonparser.model.deprecated.Package;
//import jsonparser.model.json.value.impl.JObject;
//import jsonparser.model.deprecated.PositionPackage;
//import jsonparser.service.deprecated.IJsonFactory;
//import jsonparser.service.deprecated.ISymbolService;
//import jsonparser.service.deprecated.IValueService;
//
//import java.io.Serializable;
//@Deprecated
//public class SymbolService implements ISymbolService {
//
//    /**
//     * @param character next symbol of string
//     * @return returns true if symbol is a number
//     */
//    public boolean isNumber(Character character) {
//        final String NUMBERS = "1234567890.";
//        return NUMBERS.contains(character.toString());
//    }
//
//    /**
//     * defines type of json value based on
//     * first character of its value
//     */
//    public ValueType getInnerType(String innerValue, int position) {
//        while (true) {
//            Character nextSymbol = getNextChar(innerValue, position);
//            if (nextSymbol == null) {
//                return ValueType.NONE;
//            }
//            if (isNumber(nextSymbol)) {
//                return ValueType.NUMBER;
//            }
//            switch (nextSymbol) {
//                case '{':
//                    return ValueType.OBJECT;
//                case '[':
//                    return ValueType.ARRAY;
//                case '"':
//                    return ValueType.STRING;
//                case 'n':
//                    return ValueType.NULL;
//                case 't':
//                case 'f':
//                    return ValueType.BOOLEAN;
//                case ':':
//                case ',':
//                    position++;
//                    continue;
//            }
//            return ValueType.NONE;
//        }
//    }
//
//    /**
//     * creates mainJson object
//     * reads json string char by char
//     * if {@link ValueService#createField(String)} setJName}
//     * returns an empty package, stops adding elements to mainJson's element list
//     */
//    public Json goThroughString(String jsonString) {
//        IValueService valueService = new ValueService();
//        IJsonFactory jsonFactory = new JsonFactory();
//        Json mainJson = new Json();
//        ValueType valueType = getInnerType(jsonString,-1);
//        JValue mainValue = jsonFactory.createValue(valueType);
//        mainJson.setValue(mainValue);
//        switch (valueType){
//            case OBJECT:
//                return objectLogic(jsonString, valueService, mainJson, mainValue);
//            case NUMBER:
//                return numberLogic(jsonString, valueService, mainJson, mainValue);
//            case STRING:
//                return stringLogic(jsonString, valueService, mainJson, mainValue);
////todo
////            case BOOLEAN:
////                return stringLogic(jsonString, valueService, mainJson, mainValue);
////            case NULL:
////                return stringLogic(jsonString, valueService, mainJson, mainValue);
////            case ARRAY:
////                return stringLogic(jsonString, valueService, mainJson, mainValue);
//
//        }
//
//        return mainJson;
//    }
//
//    private Json objectLogic(String jsonString, IValueService valueService, Json mainJson, JValue mainValue){
//        Package pack;
//        jsonString = jsonString.substring(jsonString.indexOf("{") + 1, jsonString.lastIndexOf("}"));
//        for (int charIndex = 0; charIndex < jsonString.length(); ) {
//            if (jsonString.charAt(charIndex) == '"') {
//                jsonString = jsonString.substring(charIndex);
//                pack = valueService.createField(jsonString);
//                if (pack == null) {
//                    return mainJson;
//                }
//                JObject mainObject = (JObject) mainValue;
//                mainObject.getFields().add(pack.getPair());
//                jsonString = pack.getUpdatedString();
//                charIndex = 0;
//            } else {
//                charIndex++;
//            }
//        }
//        return mainJson;
//    }
//
//    private Json numberLogic(String jsonString, IValueService valueService, Json mainJson, JValue mainValue){
//        PositionPackage receivedPackage = valueService.getNumberValue(jsonString);
//        JNumber mainNumber = (JNumber) mainValue;
//        mainNumber.setValue((Serializable) receivedPackage.getValue());
//        return mainJson;
//    }
//
//    private Json stringLogic(String jsonString, IValueService valueService, Json mainJson, JValue mainValue){
//        String stringValue = valueService.getQuotedValue(jsonString);
//        if (stringValue==null){
//            throw new RuntimeException("Incorrect input. Wrong [\"] symbol placement");
//        }
//        JString mainString = (JString) mainValue;
//        mainString.setValue(stringValue);
//        return mainJson;
//    }
//
//    /**
//     * returns next char of a string ignoring spaces
//     */
//    public Character getNextChar(String string, int position) {
//        for (int charIndex = position + 1; charIndex < string.length(); charIndex++) {  // to remove +1
//            char currentChar = string.charAt(charIndex);
//            if (!(currentChar == ' ' || currentChar == '\n')) {
//                return currentChar;
//            }
//        }
//        return null;
//    }
//
//
//}
