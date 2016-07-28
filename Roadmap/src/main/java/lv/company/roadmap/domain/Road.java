package lv.company.roadmap.domain;

import lv.company.roadmap.Validate;

public class Road {

    private final CityPair cities;
    private final String name;
    private final long length;

    public Road(CityPair cities, String name, long length) {
        Validate.notNull(name, "Road name is mandatory");
        Validate.notNull(cities, "Cities pair connected by the road are mandatory");

        this.cities = cities;
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public long getLength() {
        return length;
    }

    public CityPair getCities() {
        return cities;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Road road = (Road) o;

        if (length != road.length) return false;
        if (cities != null ? !cities.equals(road.cities) : road.cities != null) return false;
        return name != null ? name.equals(road.name) : road.name == null;

    }

    @Override
    public int hashCode() {
        int result = cities != null ? cities.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (length ^ (length >>> 32));
        return result;
    }
}
