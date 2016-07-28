package lv.company.roadmap.domain;

import lv.company.roadmap.Validate;

public class CityPair {

    private City from;
    private City to;

    public CityPair(City from, City to) {
        Validate.notNull(from);
        Validate.notNull(to);
        this.from = from;
        this.to = to;
    }

    public City getFrom() {
        return from;
    }

    public City getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityPair cityPair = (CityPair) o;

        if (from != null ? !from.equals(cityPair.from) : cityPair.from != null) return false;
        return to != null ? to.equals(cityPair.to) : cityPair.to == null;

    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }
}
