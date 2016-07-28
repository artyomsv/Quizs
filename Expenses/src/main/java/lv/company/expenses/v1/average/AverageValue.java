package lv.company.expenses.v1.average;


import lv.company.expenses.v1.LongNumber;

import java.util.List;
import java.util.OptionalDouble;

public class AverageValue implements Average {

    private final List<? extends LongNumber> expanses;

    public AverageValue(List<? extends LongNumber> expanses) {
        this.expanses = expanses;
    }

    @Override
    public long average() {
        OptionalDouble average = expanses.stream()
                .mapToLong(LongNumber::toLong)
                .average();
        if (average.isPresent()) {
            return (long) average.getAsDouble();
        }
        throw new IllegalArgumentException("Failed to determine average value");
    }
}
