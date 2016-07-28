package lv.company.roadmap.service.city;

import lv.company.roadmap.Validate;
import lv.company.roadmap.domain.City;

import java.util.concurrent.ConcurrentHashMap;

class InMemoryCityRepository implements CityRepository {

    private ConcurrentHashMap<String, City> cities = new ConcurrentHashMap<String, City>();

    public void add(City city) {
        Validate.notNull(city, "City is null.");
        Validate.notNull(city.getName(), "Missing city name.");

        if (cities.containsKey(city.getName().toLowerCase())) {
            throw new IllegalArgumentException("City with simillar name is already saved.");
        }

        cities.put(city.getName().toLowerCase(), city);
    }

    public City find(String name) {
        return cities.get(name.toLowerCase());
    }
}
