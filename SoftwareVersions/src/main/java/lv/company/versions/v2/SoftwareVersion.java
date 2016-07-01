package lv.company.versions.v2;

import java.util.StringTokenizer;

public class SoftwareVersion implements Comparable<SoftwareVersion> {

    private static final String ZERO = "0";
    private static final int EQUALS = 0;
    private static final int LOWER = -1;
    private static final int HIGHER = 1;

    private final String version;

    public SoftwareVersion(String version) {
        this.version = version;
    }

    @Override
    public int compareTo(SoftwareVersion o) {
        this.validate();
        o.validate();

        return compare(new StringTokenizer(this.version, "."), new StringTokenizer(o.version, "."));
    }

    private int compare(StringTokenizer leftTokenizer, StringTokenizer rightTokenizer) {
        if (leftTokenizer.hasMoreElements() && rightTokenizer.hasMoreElements()) {

            int result = new Signum(leftTokenizer.nextToken(), rightTokenizer.nextToken()).signum();

            if (result != EQUALS) {
                return result;
            } else if (!leftTokenizer.hasMoreElements()) {
                return checkLastDigits(rightTokenizer, LOWER);
            } else if (!rightTokenizer.hasMoreElements()) {
                return checkLastDigits(leftTokenizer, HIGHER);
            }
        }

        return compare(leftTokenizer, rightTokenizer);
    }

    private int checkLastDigits(StringTokenizer tokenizer, int comparisonValue) {
        while (tokenizer.hasMoreElements()) {
            if (!ZERO.equals(tokenizer.nextToken())) {
                return comparisonValue;
            }
        }
        return EQUALS;
    }

    private void validate() {
        if (version == null) {
            throw new IllegalArgumentException("Version cannot be null");
        }

        if (!version.matches("(\\d+(?:\\.\\d+)+|\\d+)")) {
            throw new IllegalArgumentException("Version [" + version + "] format is not valid");
        }
    }
}
