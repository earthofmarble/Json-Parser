package jsonparser.model.json.value.impl;

import jsonparser.model.json.JPair;
import jsonparser.model.json.value.JValue;

import java.util.ArrayList;
import java.util.List;

public class JObject implements JValue {

    List<JPair> fields;

    public JObject() {
        fields = new ArrayList<>();
    }

    public void addField(JPair pair){
        fields.add(pair);
    }

    public List<JPair> getFields() {
        if (fields==null){
            fields = new ArrayList<>();
        }
        return fields;
    }

    public void setFields(List<JPair> fields) {
        this.fields = fields;
    }
}
