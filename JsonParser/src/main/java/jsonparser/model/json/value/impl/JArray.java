package jsonparser.model.json.value.impl;

import jsonparser.model.json.value.JValue;

import java.util.ArrayList;
import java.util.List;

public class JArray implements JValue {

    List<JValue> elements = new ArrayList<>();

    public void addElement(JValue element){
        elements.add(element);
    }


    public List<JValue> getElements() {
        return elements;
    }

    public void setElements(List<JValue> elements) {
        this.elements = elements;
    }
}
