package jsonparser.service.deprecated;

import jsonparser.enumeration.ValueType;
import jsonparser.model.json.value.JValue;
@Deprecated
public interface IJsonFactory {

    JValue createValue(ValueType valueType);

}
