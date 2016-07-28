package lv.company.roadmap.service.city;

import lv.company.roadmap.domain.City;

public interface CityRepository {

    void add(City city);

    City find(String name);

}
