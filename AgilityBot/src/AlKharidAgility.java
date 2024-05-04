import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;

public class AlKharidAgility extends AgilityActivity{
    AlKharidAgility() {
        agilityObstacles.add(new AgilityObstacleArea(new Area(3270, 3198, 3276, 3195), 11633));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3271, 3192, 3277, 3181, 3), 14398));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3265, 3173, 3272, 3161, 3),14402));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3283, 3176, 3302, 3160, 3), 14403));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3313, 3165, 3318, 3160, 1), 14404));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3317, 3174, 3312, 3179, 2), 11634));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3312, 3186, 3318, 3180, 3), 14409));
        agilityObstacles.add(new AgilityObstacleArea(new Area(
                new Tile[] {
                        new Tile(3297, 3191, 3),
                        new Tile(3300, 3194, 3),
                        new Tile(3305, 3189, 3),
                        new Tile(3302, 3186, 3)
                }), 14399));
    }

}
