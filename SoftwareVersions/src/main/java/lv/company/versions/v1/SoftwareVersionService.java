package lv.company.versions.v1;

import java.util.StringTokenizer;

public class SoftwareVersionService {

    private static final String ZERO = "0";

    public int compareVersions(String v1, String v2) {
        isVersionArgumentValid(v1);
        isVersionArgumentValid(v2);


        StringTokenizer firstVersion = new StringTokenizer(v1, "\\.");
        StringTokenizer secondVersion = new StringTokenizer(v2, "\\.");

        return getComparisonResult(firstVersion, secondVersion);

    }

    private int getComparisonResult(StringTokenizer firstVersion, StringTokenizer secondVersion) {
        int result = 0;
        while (true) {
            int bufferResult;
            if (firstVersion.hasMoreElements() && secondVersion.hasMoreElements()) {
                Integer firstValue = Integer.valueOf(firstVersion.nextToken());
                Integer secondValue = Integer.valueOf(secondVersion.nextToken());

                bufferResult = firstValue.compareTo(secondValue);

                if (bufferResult != 0) {
                    return bufferResult;
                } else if (!firstVersion.hasMoreElements()) {
                    return checkLastDigits(secondVersion, -1);
                } else if (!secondVersion.hasMoreElements()) {
                    return checkLastDigits(firstVersion, 1);
                }

                result = bufferResult;
            }

            if (!firstVersion.hasMoreElements() || !secondVersion.hasMoreElements()) {
                return result;
            }

        }
    }

    private int checkLastDigits(StringTokenizer tokenizer, int compareResult) {
        while (tokenizer.hasMoreElements()) {
            if (!ZERO.equals(tokenizer.nextToken())) {
                return compareResult;
            }
        }
        return 0;
    }


    private void isVersionArgumentValid(String version) {
        if (version == null) {
            throw new IllegalArgumentException("Version value is null");
        } else if (version.endsWith(".")) {
            throw new IllegalArgumentException("Version value ends with '.'");
        }

        for (char c : version.toCharArray()) {
            if (c == '.') {
                continue;
            } else if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("Version contains not valid character 'c'");
            }
        }
    }
}
