package jsonparser.service.upd;

import jsonparser.enumeration.ValueType;
import jsonparser.model.json.Json;

public interface ISymbolService {

    boolean isNumber(Character character);

    boolean isNull(Character character);

    boolean isBoolean(Character character);

    ValueType getInnerType(String innerValue, int position);

    Character getNextChar(String string, int position);

}
