package jsonparser.service.parser;

import jsonparser.model.json.value.JsonValue;

public interface IParser {

    JsonValue parseJson(String jsonString);

}
