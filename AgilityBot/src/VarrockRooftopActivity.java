import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;

public class VarrockRooftopActivity extends AgilityActivity {
    VarrockRooftopActivity() {
        agilityObstacles.add(new AgilityObstacleArea(new Area(3221, 3411, 3224, 3419), 14412));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3213, 3419, 3219, 3410, 3), 14413));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3200, 3419, 3208, 3413, 3), 14414));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3192, 3416, 3198, 3416, 1), 14832));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3191, 3406, 3192, 3402, 3), 14833));
        agilityObstacles.add(new AgilityObstacleArea(new Area(
                new Tile[] {
                        new Tile(3182, 3389, 3),
                        new Tile(3182, 3399, 3),
                        new Tile(3201, 3399, 3),
                        new Tile(3201, 3405, 3),
                        new Tile(3209, 3405, 3),
                        new Tile(3209, 3394, 3),
                        new Tile(3201, 3394, 3),
                        new Tile(3196, 3389, 3)
                }), 14834));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3218, 3402, 3232, 3393, 3), 14835));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3235, 3403, 3240, 3409, 3), 14836));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3235, 3410, 3240, 3416, 3), 14841));
    }
}
