package jsonparser.model.json.value.impl;

import jsonparser.model.json.value.JsonValue;

import java.util.ArrayList;
import java.util.List;

public class JsonArray implements JsonValue {

    private List<JsonValue> elements = new ArrayList<>();

    public void addElement(JsonValue element) {
        elements.add(element);
    }


    public List<JsonValue> getElements() {
        return elements;
    }

    public void setElements(List<JsonValue> elements) {
        this.elements = elements;
    }
}
