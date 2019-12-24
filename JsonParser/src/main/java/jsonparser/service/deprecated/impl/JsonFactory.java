//package jsonparser.service.deprecated.impl;
//
//import jsonparser.enumeration.ValueType;
//import jsonparser.model.json.value.JValue;
//import jsonparser.model.json.value.impl.*;
//import jsonparser.service.deprecated.IJsonFactory;
//
//@Deprecated
//public class JsonFactory implements IJsonFactory {
//
//    /**
//     * @return returns an instance of AbstractJson based on given JsonType
//     */
//    public JValue createValue(ValueType valueType) {
//        switch (valueType) {
//            case OBJECT:
//                return new JObject();
//            case ARRAY:
//                return new JArray();
//            case STRING:
////                return new JString();
//            case NUMBER:
//                return new JNumber();
//            case NULL:
//                return new JNull();
//            case BOOLEAN:
//                return new JBoolean();
//        }
//        throw new RuntimeException("Wrong json type. Required: [OBJECT, ARRAY, " +
//                "STRING, NUMBER, NULL, BOOLEAN], found [" + valueType + "]");
//    }
//
//
//}
