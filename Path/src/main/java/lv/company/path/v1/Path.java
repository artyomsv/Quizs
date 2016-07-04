package lv.company.path.v1;

public class Path {

    public static int calculate(byte[][] array) {
        return calculate(array, 0, 0, 0);
    }


    private static int calculate(byte[][] array, int n, int m, int possibleWaysCount) {
        if (array == null) {
            throw new IllegalArgumentException("Input data is null");
        }

        if (outOfArrayBorders(array, n, m) || blockedByZero(array[n][m])) {
            return possibleWaysCount;
        }

        if (reachedTheEnd(array, n, m)) {
            return possibleWaysCount + 1;
        }

        if (n < array.length) {
            possibleWaysCount = calculate(array, n + 1, m, possibleWaysCount);
        }
        if (m < array[n].length) {
            possibleWaysCount = calculate(array, n, m + 1, possibleWaysCount);
        }

        return possibleWaysCount;
    }

    private static boolean outOfArrayBorders(byte[][] array, int n, int m) {
        return n >= array.length || m >= array[n].length;
    }

    private static boolean blockedByZero(byte b) {
        return b == 0;
    }

    private static boolean reachedTheEnd(byte[][] array, int n, int m) {
        return n == array.length - 1 && m == array[n].length - 1;
    }

}
