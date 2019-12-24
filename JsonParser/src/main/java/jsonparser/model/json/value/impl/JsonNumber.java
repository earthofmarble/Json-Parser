package jsonparser.model.json.value.impl;

import jsonparser.model.json.value.JsonValue;


public class JsonNumber implements JsonValue {

    private Number value;

    public JsonNumber(Number value) {
        this.value = value;
    }

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }
}
