package jsonparser.model.pack;

public class ObjectWithPosition {

    private Object objectValue;
    private int position;

    public ObjectWithPosition(Object objectValue, int position) {
        this.objectValue = objectValue;
        this.position = position;
    }

    public Object getObjectValue() {
        return objectValue;
    }

    public void setObjectValue(Object objectValue) {
        this.objectValue = objectValue;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
