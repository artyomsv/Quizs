package lv.company.roadmap.service.road;

import lv.company.roadmap.domain.Road;

import java.util.Set;

public interface RoadRepository {

    void add(Road road);

    void remove(Road road);

    Set<Road> getRoads(String cityName);

}
