package jsonparser.model.json.value.impl;

import jsonparser.model.json.value.JValue;

public class JBoolean implements JValue {

    Boolean value;

    public JBoolean(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
