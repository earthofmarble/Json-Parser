package jsonparser.model.json.value.impl;

import jsonparser.model.json.value.JValue;

import java.util.ArrayList;
import java.util.List;

public class JArray implements JValue {

    List<JValue> elements;

    public List<JValue> getElements() {
        if (elements==null){
            elements = new ArrayList<>();
        }
        return elements;
    }

    public void setElements(List<JValue> elements) {
        this.elements = elements;
    }
}
