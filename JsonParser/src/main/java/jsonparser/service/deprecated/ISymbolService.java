package jsonparser.service.deprecated;

import jsonparser.enumeration.ValueType;
import jsonparser.model.json.Json;
@Deprecated
public interface ISymbolService {

    Json goThroughString(String jsonString);

    boolean isNumber(Character character);

    ValueType getInnerType(String innerValue, int position);

    Character getNextChar(String string, int position);

}
