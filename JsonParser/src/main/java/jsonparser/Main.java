package jsonparser;

import jsonparser.model.json.value.JsonValue;
import jsonparser.service.parser.IParser;
import jsonparser.service.parser.Parser;
import jsonparser.service.upd.ISymbolService;
import jsonparser.service.upd.impl.ParseService;
import jsonparser.service.upd.impl.SymbolService;
import jsonparser.service.upd.impl.ValueService;

public class Main {

    public static void main(String[] args) {
        String bigJson = "{\"owner\": { \"id\": { \"innerId\": 1234, \"innerId2\": { \"innerInnerID\": 15555," +
                " \"innerInnerId\": 7889 } }, \"id2\": 2 }, \"adName\": \"zzzzz\"," +
                " \"adPrice\": 14, \"adPhotoUrl\": { \"url\": \"xxx\", \"isMain\": true }, \"nullField\": null }";

        String arrayJson = "{\"owner\": { \"id\": { \"innerId\": 1234, \"innerId2\": { \"innerInnerID\": 15555," +
                " \"innerInnerId\": 7889   }   }  ,  \"id2\": 2 }, \"adName\": \"zzzzz\" ,  " +
                " \"adPrice\": 14   ,   \"array\"   :   [   { \"arrEl1\": \"arVal1\", \"arEl2\": \"arVal2\" }," +
                " { \"arEl111\": \"arVal1111\"  ,  \"arEl2222\":  \"arVal2222\" }   , 1, \"asdasd\", null, true ], \"adPhotoUrl\"  : { \"url\": \"xxx\", \"isMain\": true }  , \"nullField\": null  }  ";
        String emptyJson = "[123, 34, 5]";
        String stringJson = " \"qwe\"";
        String numberJson = "12133.12";
        String nullJson = "null";
        String easyJson = "{\"attr\":{\"iA1\": 211.23, \"iA2\": false}, \"attr22\":\"213\"}";
        ISymbolService symbolService = new SymbolService();
        IParser parser = new Parser(new ParseService(symbolService, new ValueService(symbolService)));
        JsonValue mainJson = parser.parseJson(arrayJson);
        System.out.println(mainJson);
    }
}
