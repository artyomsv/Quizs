package lv.company.versions.v2;

public class NumericVersion implements Version {

    private final Version version;

    public NumericVersion(Version version) {
        this.version = version;
    }

    @Override
    public int compareTo(Version o) {
        if (!matches(version.version()) || !matches(o.version())) {
            throw new IllegalArgumentException("Version [" + version + "] format is not valid");
        }

        return this.version.compareTo(o);
    }

    private boolean matches(String version) {
        return version.matches("(\\d+(?:\\.\\d+)+|\\d+)");
    }

    @Override
    public String version() {
        return version.version();
    }
}
