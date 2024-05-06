import SharedBotLib.LootItem;
import org.dreambot.api.methods.map.Area;
import SharedBotLib.UserAreas;
import SharedBotLib.Utils;

public class FightingMenInEdgevilleActivity extends FightingActivity{
    FightingMenInEdgevilleActivity() {
        fightingArea = UserAreas.EdgevilleMenArea;
        bankArea = UserAreas.EdgevillBankArea;
        enemyMaxHit = 1;
        enemyName = "Man";
        shouldLoot = true;
        minHealingFood = 3;

        itemsToLoot.add(new LootItem("Grimy ranarr weed", 102));
        itemsToLoot.add(new LootItem("Grimy cadantine", 100));
        itemsToLoot.add(new LootItem("Grimy lantadyme", 99));
        itemsToLoot.add(new LootItem("Grimy kwuarm", 98));
        itemsToLoot.add(new LootItem("Grimy avantoe", 97));
        itemsToLoot.add(new LootItem("Grimy irit", 99));
        itemsToLoot.add(new LootItem("Clue scroll (easy)", 101));
    }
}
