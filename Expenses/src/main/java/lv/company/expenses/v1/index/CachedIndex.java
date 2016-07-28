package lv.company.expenses.v1.index;

import java.util.ArrayList;
import java.util.List;

public class CachedIndex implements Index {

    private final Index index;
    private final List<Integer> cache = new ArrayList<>(1);

    public CachedIndex(Index index) {
        this.index = index;
    }


    @Override
    public int index() {
        if (cache.isEmpty()) {
            cache.add(this.index.index());
        }
        return cache.get(0);
    }

}
