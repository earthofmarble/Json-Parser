package jsonparser;

import jsonparser.model.json.value.JsonValue;
import jsonparser.parser.IParser;
import jsonparser.parser.Parser;
import jsonparser.service.ISymbolService;
import jsonparser.service.impl.ParseService;
import jsonparser.service.impl.SymbolService;
import jsonparser.service.impl.ValueService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String bigJson = "{\"owner\": { \"id\": { \"innerId\": 1234, \"innerId2\": { \"innerInnerID\": 15555," +
                " \"innerInnerId\": 7889 } }, \"id2\": 2 }, \"adName\": \"zzzzz\"," +
                " \"adPrice\": 14, \"adPhotoUrl\": { \"url\": \"xxx\", \"isMain\": true }, \"nullField\": null }";

        String arrayJson = "{\"owner\": { \"id\": { \"innerId\": 1234, \"innerId2\": { \"innerInnerID\": 15555," +
                " \"innerInnerId\": 7889   }   }  ,  \"id2\": 2 }, \"adName\": \"zzzzz\" ,  " +
                " \"adPrice\": 14   ,   \"array\"   :   [   { \"arrEl1\": \"arVal1\", \"arEl2\": \"arVal2\" }," +
                " { \"arEl111\": \"arVal1111\"  ,  \"arEl2222\":  \"arVal2222\" }   , 1, \"asdasd\", null, true ], \"adPhotoUrl\"  : { \"url\": \"xxx\", \"isMain\": true }  , \"nullField\": null  }  ";
        String emptyJson = "";
        String stringJson = " \"qwe\"";
        String numberJson = "12133.12";
        String nullJson = "null";
        String easyJson = "{\"attr\":{\"iA1\": 211.23, \"iA2\": false}, \"attr22\":\"213\"}";
        ISymbolService symbolService = new SymbolService();
        IParser parser = new Parser(new ParseService(symbolService, new ValueService(symbolService)));
        JsonValue mainJson = parser.parseJson(bigJson);
        try {
            String content = new Scanner(new File("/home/INTEXSOFT/leonid.keda/Downloads/json.txt")).useDelimiter("\\Z").next();
            System.out.println(content);
            JsonValue fileJson = parser.parseJson(content);
            System.out.println(fileJson);
        } catch (FileNotFoundException e) {
            e.printStackTrace();//TODO
        }
        System.out.println(mainJson);
    }
}
