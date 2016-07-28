package lv.company.roadmap.service.road;

public class RoadRepositoryFactory {

    public static final RoadRepository INSTANCE = new InMemoryRoadRepository();

}
