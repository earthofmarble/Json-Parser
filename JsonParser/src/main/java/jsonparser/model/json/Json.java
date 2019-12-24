package jsonparser.model.json;

import jsonparser.model.json.value.JValue;

public class Json {

    JValue value;

    public JValue getValue() {
        return value;
    }

    public void setValue(JValue value) {
        this.value = value;
    }
}
