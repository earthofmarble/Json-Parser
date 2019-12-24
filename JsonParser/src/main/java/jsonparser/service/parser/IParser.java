package jsonparser.service.parser;

import jsonparser.model.json.Json;
import jsonparser.model.json.value.JValue;

public interface IParser {

    JValue parseJson(String jsonString);

}
