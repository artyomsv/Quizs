package lv.company.roadmap.domain;

import lv.company.roadmap.Validate;

public class City {

    private final String name;
    private final Location coordinates;

    public City(String name, Location coordinates) {
        Validate.notNull(name, "City name is mandatory.");
        Validate.notNull(coordinates, "City coordinates are mandatory");

        this.name = name;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public Location getCoordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (name != null ? !name.equals(city.name) : city.name != null) return false;
        return coordinates != null ? coordinates.equals(city.coordinates) : city.coordinates == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (coordinates != null ? coordinates.hashCode() : 0);
        return result;
    }
}
