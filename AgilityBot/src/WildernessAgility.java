import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;

public class WildernessAgility  extends AgilityActivity{
    WildernessAgility() {
        isWilderness = true;
        agilityObstacles.add(new AgilityObstacleArea(new Area(3002, 3937, 3005, 3934), 23137));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3003, 3940, 3006, 3953), 23132));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3001, 3964, 3007, 3955), 23556));
        agilityObstacles.add(new AgilityObstacleArea(new Area(
                new Tile[] {
                        new Tile(2999, 3966, 0),
                        new Tile(3002, 3954, 0),
                        new Tile(3002, 3943, 0),
                        new Tile(2995, 3943, 0),
                        new Tile(2995, 3949, 0),
                        new Tile(2990, 3953, 0),
                        new Tile(2990, 3963, 0)
                }
        ), 23542));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3000, 3938, 2988, 3949), 23640));

    }
}
