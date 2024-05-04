import SharedBotLib.Activity;
import SharedBotLib.LootItem;
import org.dreambot.api.methods.map.Area;

import java.util.ArrayList;
import java.util.List;

public class FightingActivity extends Activity {
    public Area bankArea;
    public Area fightingArea;
    public int enemyMaxHit;
    public String enemyName;
    public boolean shouldLoot;
    public List<LootItem> itemsToLoot = new ArrayList<>();
}