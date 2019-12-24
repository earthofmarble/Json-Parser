package jsonparser.model.pack;

public class StringPositionPackage {

    private String string;
    private int position;

    public StringPositionPackage(String string, int position) {
        this.string = string;
        this.position = position;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
