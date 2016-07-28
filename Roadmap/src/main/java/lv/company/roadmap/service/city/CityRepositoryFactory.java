package lv.company.roadmap.service.city;

public class CityRepositoryFactory {

    public static final CityRepository INSTANCE = new InMemoryCityRepository();

}
