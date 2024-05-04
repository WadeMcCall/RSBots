import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;

public class DraynorAgilityActivity extends AgilityActivity{
    DraynorAgilityActivity() {
        agilityObstacles.add(new AgilityObstacleArea(new Area(3103, 3281, 3106, 3274), 11404));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3097, 3281, 3102, 3277, 3), 11405));
        agilityObstacles.add(new AgilityObstacleArea(new Area(
                new Tile[] {
                        new Tile(3087, 3274, 3),
                        new Tile(3090, 3278, 3),
                        new Tile(3093, 3276, 3),
                        new Tile(3090, 3272, 3)
                }
        ),11406));

        agilityObstacles.add(new AgilityObstacleArea(new Area(3089, 3268, 3093, 3265, 3), 11430));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3087, 3261, 3088, 3257, 3), 11630));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3087, 3255, 3094, 3255, 3), 11631));
        agilityObstacles.add(new AgilityObstacleArea(new Area(3096, 3261, 3101, 3256, 3), 11632));
    }
}
