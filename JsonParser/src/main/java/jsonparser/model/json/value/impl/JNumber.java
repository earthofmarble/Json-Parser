package jsonparser.model.json.value.impl;

import jsonparser.model.json.value.JValue;


public class JNumber implements JValue {

    Number value;

    public JNumber(Number value) {
        this.value = value;
    }

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }
}
