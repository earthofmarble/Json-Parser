package jsonparser.model.json.value.impl;

import jsonparser.model.json.value.JValue;

public class JString implements JValue {

    String value;

    public JString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
