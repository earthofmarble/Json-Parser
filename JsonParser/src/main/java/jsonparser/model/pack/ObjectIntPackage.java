package jsonparser.model.pack;

public class ObjectIntPackage {

    private Object objectValue;
    private int intValue;

    public ObjectIntPackage(Object objectValue, int intValue) {
        this.objectValue = objectValue;
        this.intValue = intValue;
    }

    public Object getObjectValue() {
        return objectValue;
    }

    public void setObjectValue(Object objectValue) {
        this.objectValue = objectValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }
}
