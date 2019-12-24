package jsonparser.service.upd;

import jsonparser.enumeration.ValueType;
import jsonparser.model.json.value.JValue;
import jsonparser.model.pack.PairPositionPackage;
import jsonparser.model.pack.PositionValuePackage;

public interface IValueService {

    PairPositionPackage createPair(String jsonString, int startPosition);
    PositionValuePackage createArrayValue(String jsonString, int startPosition);
    PositionValuePackage createStringValue(String jsonString, int startPosition);
    PositionValuePackage createNumericValue(String jsonString, int startPosition);
    PositionValuePackage createBooleanValue(String jsonString, int startPosition);
    PositionValuePackage createNullValue(String jsonString, int startPosition);

}
