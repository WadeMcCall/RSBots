import org.dreambot.api.methods.map.Area;
import SharedBotLib.UserAreas;
import SharedBotLib.LootItem;

public class KillingHillGiantsAtFoundryActivity extends FightingActivity{
    KillingHillGiantsAtFoundryActivity() {

        fightingArea = UserAreas.HillGiantsFoundryArea;
        bankArea = UserAreas.AlKharidBank;
        enemyMaxHit = 4;
        enemyName = "Hill Giant";
        shouldLoot = true;
        minHealingFood = 3;
        foodPriority = 3;

        itemsToLoot.add(new LootItem("Curved bone", 105));
        itemsToLoot.add(new LootItem("Giant key", 104));
        itemsToLoot.add(new LootItem("Long bone", 103));
        itemsToLoot.add(new LootItem("Snape grass seed", 100));
        itemsToLoot.add(new LootItem("Grimy ranarr weed", 99));
        itemsToLoot.add(new LootItem("Grimy cadantine", 98));
        itemsToLoot.add(new LootItem("Grimy lantadyme", 97));
        itemsToLoot.add(new LootItem("Grimy kwuarm", 96));
        itemsToLoot.add(new LootItem("Grimy avantoe", 95));
        itemsToLoot.add(new LootItem("Ensouled giant head", 12));
        itemsToLoot.add(new LootItem("Grimy irit", 11));
        itemsToLoot.add(new LootItem("Nature rune", 10));
        itemsToLoot.add(new LootItem("Law rune", 5));
        itemsToLoot.add(new LootItem("Limpwurt root", 2));
        itemsToLoot.add(new LootItem("Big bones", 1));
    }
}
