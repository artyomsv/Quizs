package lv.company.expenses.v1.index;

import lv.company.expenses.v1.LongNumber;

import java.util.List;

public class BorderIndex implements Index {

    private final List<? extends LongNumber> numbers;
    private final long value;

    public BorderIndex(List<? extends LongNumber> numbers, long value) {
        this.numbers = numbers;
        this.value = value;
    }

    @Override
    public int index() {
        for (int i = 0; i < numbers.size(); i++) {
            LongNumber number = numbers.get(i);
            if (number.toLong() < value) {
                return i;
            }
        }
        throw new IllegalArgumentException("Failed to determine borer index");

    }

}
