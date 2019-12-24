package jsonparser.service;


public interface ISymbolService {

    boolean isNumber(Character character);

    boolean isNull(Character character);

    boolean isBoolean(Character character);

    Character getNextChar(String string, int position);

}
