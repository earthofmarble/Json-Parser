package jsonparser.model.pack;

import jsonparser.model.json.JPair;

public class PairPositionPackage {

    private JPair jPair;
    private int position;

    public PairPositionPackage(JPair jPair, int position) {
        this.jPair = jPair;
        this.position = position;
    }

    public JPair getjPair() {
        return jPair;
    }

    public void setjPair(JPair jPair) {
        this.jPair = jPair;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
