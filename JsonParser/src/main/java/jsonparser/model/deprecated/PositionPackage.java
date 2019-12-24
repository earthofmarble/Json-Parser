package jsonparser.model.deprecated;
@Deprecated
public class PositionPackage {

    private Object value;
    private Integer position;

    public PositionPackage(Object value, Integer position) {
        this.value = value;
        this.position = position;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
