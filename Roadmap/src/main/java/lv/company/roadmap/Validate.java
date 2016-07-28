package lv.company.roadmap;

public class Validate {

    public static void notNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException();
        }
    }

    public static void notNull(Object o, String message) {
        if (o == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
