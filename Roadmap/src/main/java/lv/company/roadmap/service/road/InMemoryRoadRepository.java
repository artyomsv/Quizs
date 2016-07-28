package lv.company.roadmap.service.road;

import lv.company.roadmap.Validate;
import lv.company.roadmap.domain.City;
import lv.company.roadmap.domain.CityPair;
import lv.company.roadmap.domain.Road;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

class InMemoryRoadRepository implements RoadRepository {

    private final Map<String, Set<Road>> roadsMap = new HashMap<String, Set<Road>>();
    private final Set<String> roadNames = new HashSet<String>();
    private final ReentrantLock lock = new ReentrantLock();

    public void add(Road road) {
        Validate.notNull(road);
        Validate.notNull(road.getName());

        if (roadNames.contains(road.getName().toLowerCase())) {
            throw new IllegalArgumentException("Road with simillar name is already saved.");
        }

        CityPair cities = road.getCities();
        lock.lock();  // could be changed to synchronized block
        addRoadToCity(cities.getFrom(), road);
        addRoadToCity(cities.getTo(), road);
        roadNames.add(road.getName());
        lock.unlock();
    }

    public void remove(Road road) {
        lock.lock();
        CityPair cities = road.getCities();
        removeRoadFromCity(cities.getFrom(), road);
        removeRoadFromCity(cities.getTo(), road);
        roadNames.remove(road.getName().toLowerCase());
        lock.unlock();
    }

    public Set<Road> getRoads(String cityName) {
        return roadsMap.get(cityName.toLowerCase());
    }

    private void removeRoadFromCity(City city, Road road) {
        String normilizedName = city.getName().toLowerCase();
        Set<Road> roads = roadsMap.get(normilizedName);
        if (roads != null) {
            roads.remove(road);
        }
    }

    private void addRoadToCity(City city, Road road) {
        String cityNameKey = city.getName().toLowerCase();
        Set<Road> roads = this.roadsMap.get(cityNameKey);

        if (roads == null) {
            roads = new HashSet<Road>();
            roads.add(road);
        }

        this.roadsMap.put(cityNameKey, roads);
    }

}
