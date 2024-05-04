import org.dreambot.api.methods.map.Area;

public class AgilityObstacleArea {
    Area area;
    Integer nextObstacleID;

    AgilityObstacleArea(Area _area, Integer id) {
        area = _area;
        nextObstacleID = id;
    }
}
