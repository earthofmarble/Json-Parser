package jsonparser.service.parser;

import jsonparser.model.json.Json;
import jsonparser.model.json.value.JValue;
import jsonparser.service.upd.ISymbolService;
import jsonparser.service.upd.impl.ParseService;
import jsonparser.service.upd.impl.SymbolService;

public class Parser implements IParser {

    private ISymbolService symbolService;
    private ParseService parseService = new ParseService();

    public Parser() {
        symbolService = new SymbolService();
    }

    /**
     * counts left and right brackets, than compares counters
     * thrown an exception if there is any mismatch
     */
    private void validateBrackets(String jsonString) {
        int squareLeft = 0;
        int squareRight = 0;
        int curlyLeft = 0;
        int curlyRight = 0;

        for (char symbol : jsonString.toCharArray()) {
            switch (symbol) {
                case '[':
                    squareLeft++;
                    break;
                case ']':
                    squareRight++;
                    break;
                case '{':
                    curlyLeft++;
                    break;
                case '}':
                    curlyRight++;
                    break;
            }
        }
        compareCounters(squareLeft, squareRight, curlyLeft, curlyRight);
    }

    /**
     * compares counters of both bracket types
     */
    private void compareCounters(int sLeft, int sRight, int cLeft, int cRight) {
        if (sLeft != sRight || cLeft != cRight) {
            throw new RuntimeException("Incorrect input. Brackets mismatch. " +
                    "Found: [" + sLeft + "] squareLeft, [" + sRight + "] squareRight," +
                    " [" + cLeft + "] curlyLeft, [" + cRight + "] curlyRight");
        }
    }

    /**
     * validates brackets, than parses json char by char
     */
    public JValue parseJson(String jsonString) {
        validateBrackets(jsonString);
        return parseService.createJson(jsonString, 0).getValue();
    }

}

