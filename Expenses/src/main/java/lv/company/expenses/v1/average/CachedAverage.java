package lv.company.expenses.v1.average;

import java.util.ArrayList;
import java.util.List;

public class CachedAverage implements Average {

    private final Average average;
    private final List<Long> cache = new ArrayList<>(1);

    public CachedAverage(Average average) {
        this.average = average;
    }

    @Override
    public long average() {
        if (cache.isEmpty()) {
            cache.add(this.average.average());
        }
        return cache.get(0);
    }

}
