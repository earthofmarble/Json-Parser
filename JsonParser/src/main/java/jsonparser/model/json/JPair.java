package jsonparser.model.json;

import jsonparser.model.json.value.JValue;

public class JPair {

    String name;
    JValue value;

    public JPair() {
    }

    public JPair(JValue value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JValue getValue() {
        return value;
    }

    public void setValue(JValue value) {
        this.value = value;
    }
}
