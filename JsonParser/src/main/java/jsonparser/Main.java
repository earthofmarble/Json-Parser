package jsonparser;

import jsonparser.model.json.value.JValue;
import jsonparser.service.parser.IParser;
import jsonparser.service.parser.Parser;

public class Main {

    public static void main(String[] args) {
        String bigJson = "{\"owner\": { \"id\": { \"innerId\": 1234, \"innerId2\": { \"innerInnerID\": 15555," +
                " \"innerInnerId\": 7889 } }, \"id2\": 2 }, \"adName\": \"zzzzz\"," +
                " \"adPrice\": 14, \"adPhotoUrl\": { \"url\": \"xxx\", \"isMain\": true }, \"nullField\": null }";

        String arrayJson = "{\"owner\": { \"id\": { \"innerId\": 1234, \"innerId2\": { \"innerInnerID\": 15555," +
                " \"innerInnerId\": 7889 } }, \"id2\": 2 }, \"adName\": \"zzzzz\"," +
                " \"adPrice\": 14, \"array\": [ { \"arrEl1\": \"arVal1\", \"arEl2\": \"arVal2\" }," +
                " { \"arEl111\": \"arVal1111\", \"arEl2222\": \"arVal2222\" }, 1, \"asdasd\", null, true ], \"adPhotoUrl\": { \"url\": \"xxx\", \"isMain\": true }, \"nullField\": null }";

        String emptyJson = "[]";
        String stringJson = " \"qwe\"";
        String numberJson = "12133.12";
        String nullJson = "null";
        String easyJson = "{\"attr\":{\"iA1\": 211.23, \"iA2\": false}, \"attr22\":\"213\"}";
        IParser parser = new Parser();
        JValue mainJson = parser.parseJson(easyJson);
        System.out.println(mainJson);
    }
}
