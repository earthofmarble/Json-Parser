package jsonparser.model.json.value.impl;

import jsonparser.model.json.value.JsonValue;

public class JsonString implements JsonValue {

    private String value;

    public JsonString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
