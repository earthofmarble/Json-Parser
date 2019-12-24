package jsonparser.model.json.value.impl;

import jsonparser.model.json.JsonPair;
import jsonparser.model.json.value.JsonValue;

import java.util.ArrayList;
import java.util.List;

public class JsonObject implements JsonValue {

    private List<JsonPair> fields = new ArrayList<>();

    public void addField(JsonPair pair) {
        fields.add(pair);
    }

    public List<JsonPair> getFields() {
        return fields;
    }

    public void setFields(List<JsonPair> fields) {
        this.fields = fields;
    }
}
