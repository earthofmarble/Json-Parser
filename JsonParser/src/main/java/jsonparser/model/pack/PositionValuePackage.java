package jsonparser.model.pack;

import jsonparser.model.json.value.JValue;

public class PositionValuePackage {

    private int position;
    private JValue value;

    public PositionValuePackage(int position, JValue value) {
        this.position = position;
        this.value = value;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public JValue getValue() {
        return value;
    }

    public void setValue(JValue value) {
        this.value = value;
    }
}
