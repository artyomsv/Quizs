package lv.company.versions.v2;

public class StringAsInteger {

    private final String value;

    public StringAsInteger(String value) {
        this.value = value;
    }

    public int toInt() {
        return Integer.parseInt(value);
    }

}
