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

        itemsToLoot.add(new LootItem("Curved bone", 101));
        itemsToLoot.add(new LootItem("Giant Key", 100));
        itemsToLoot.add(new LootItem("Long bone", 99));
        itemsToLoot.add(new LootItem("Nature rune", 10));
        itemsToLoot.add(new LootItem("Law rune", 5));
        itemsToLoot.add(new LootItem("Trout", 3));
        itemsToLoot.add(new LootItem("Limpwurt root", 2));
        itemsToLoot.add(new LootItem("Big bones", 1));
    }
}
