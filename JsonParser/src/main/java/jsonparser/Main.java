package jsonparser;

import jsonparser.model.json.value.JValue;
import jsonparser.service.parser.IParser;
import jsonparser.service.parser.Parser;

public class Main {

    public static void main(String[] args) {
        String string = "{\"owner\": { \"id\": { \"innerId\": 1234, \"innerId2\": { \"innerInnerID\": 15555," +
                " \"innerInnerId\": 7889 } }, \"id2\": 2 }, \"adName\": \"zzzzz\"," +
                " \"adPrice\": 14, \"adPhotoUrl\": { \"url\": \"xxx\", \"isMain\": true }, \"nullField\": null }";

        String string2 = " \"qwe\"";
        String string4 = "12133.12";
        String string5 = "null";
        String string3 = "{\"attr\":{\"iA1\": 211.23, \"iA2\": false}, \"attr22\":\"213\"}";
        IParser parser = new Parser();
        JValue mainJson = parser.parseJson(string);
        System.out.println(mainJson);
    }
}
