package jsonparser.model.json;

import jsonparser.model.json.value.JsonValue;

public class JsonPair {

    private String name;
    private JsonValue value;

    public JsonPair(String name, JsonValue value) {
        this.name = name;
        this.value = value;
    }

    public JsonPair(JsonValue value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonValue getValue() {
        return value;
    }

    public void setValue(JsonValue value) {
        this.value = value;
    }
}
