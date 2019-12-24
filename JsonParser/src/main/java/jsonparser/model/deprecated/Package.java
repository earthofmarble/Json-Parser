package jsonparser.model.deprecated;

import jsonparser.model.json.JPair;
@Deprecated
public class Package {

    private JPair pair;
    private String updatedString;

    public Package(JPair pair, String updatedString) {
        this.pair = pair;
        this.updatedString = updatedString;
    }

    public JPair getPair() {
        return pair;
    }

    public void setPair(JPair pair) {
        this.pair = pair;
    }

    public String getUpdatedString() {
        return updatedString;
    }

    public void setUpdatedString(String updatedString) {
        this.updatedString = updatedString;
    }
}
