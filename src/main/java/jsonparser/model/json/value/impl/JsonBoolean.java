package jsonparser.model.json.value.impl;

import jsonparser.model.json.value.JsonValue;

public class JsonBoolean implements JsonValue {

    private Boolean value;

    public JsonBoolean(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
