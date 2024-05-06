package SharedBotLib;

import org.dreambot.api.methods.combat.Combat;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.api.wrappers.items.Item;

import java.util.Random;

public class Utils {
    public static final String[] EMPTY_STRING_ARRAY = {};
    public static final Random rand = new Random();

    public static Boolean checkIfTileAroundHasPlayer(GameObject go){
        Tile temp=new Tile(go.getX(),go.getY());
        for(Player i: Players.all()){
            if( temp.getArea(1).contains(i)&&!i.getName().equals(Players.getLocal().getName())){
                return true;
            }
        }

        return false;
    }

    public static boolean Eat() {
        Item food = Inventory.get(item -> item != null && item.hasAction("Eat"));
        if (food != null) {
            food.interact("Eat");
            return true;
        }
        return false;
    }

    public static int getCurrentHealthAsInt() {
        int maxHp = Skills.getRealLevel(Skill.HITPOINTS);
        double hpPercentage = (double) Combat.getHealthPercent() / (double) 100.0;

        return (int) (hpPercentage * maxHp);
    }

    public static int getRandomInt(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static double getRandomGuassianDistNotNegative(double center, double stdDev) {
        double randNum = rand.nextGaussian() * stdDev + center;
        return randNum >= 0 ? randNum : 0.0;
    }


    public static Tile getRandomEmptyNearbyTile() {
        Area nearbyArea = Players.getLocal().getSurroundingArea(6);
        Tile[] tiles = nearbyArea.getTiles();
        Tile tileToMoveTo = null;
        for (Tile tile : tiles) {
            GameObject[] go = GameObjects.getObjectsOnTile(tile);
            if (go.length == 0) {
                tileToMoveTo = tile;
                break;
            }
        }
        return tileToMoveTo;
    }
}
