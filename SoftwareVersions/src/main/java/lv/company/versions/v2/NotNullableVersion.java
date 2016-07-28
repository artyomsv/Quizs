package lv.company.versions.v2;

public class NotNullableVersion implements Version {

    private final Version version;

    public NotNullableVersion(Version version) {
        this.version = version;
    }

    @Override
    public int compareTo(Version o) {
        if (version.version() == null || o.version() == null) {
            throw new IllegalArgumentException("Version cannot be null");
        }

        return this.version.compareTo(o);
    }

    @Override
    public String version() {
        return version.version();
    }
}
