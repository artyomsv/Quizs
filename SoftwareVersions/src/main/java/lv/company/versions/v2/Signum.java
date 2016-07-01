package lv.company.versions.v2;

public class Signum {

    private final String value1;
    private final String value2;

    public Signum(String value1, String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public int signum() {
        return Integer.signum(Integer.valueOf(value1) - Integer.valueOf(value2));
    }

}
