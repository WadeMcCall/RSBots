import SharedBotLib.LootItem;
import SharedBotLib.UserAreas;

public class MossGiantsInVarrockSewers extends FightingActivity {
    MossGiantsInVarrockSewers() {
        bankArea = UserAreas.EdgevillBankArea;
        fightingArea = UserAreas.EdgevilleChillMossGiants;
        enemyMaxHit = 6;
        enemyName = "Moss giant";
        shouldLoot = true;
        minHealingFood = 4;
        foodPriority = 3;

        itemsToLoot.add(new LootItem("Shield left half", 115));
        itemsToLoot.add(new LootItem("Dragon spear", 114));
        itemsToLoot.add(new LootItem("Giant champion scroll", 113));
        itemsToLoot.add(new LootItem("Curved bone", 112));
        itemsToLoot.add(new LootItem("Mossy key", 111));
        itemsToLoot.add(new LootItem("Snapdragon seed", 110));
        itemsToLoot.add(new LootItem("Ranarr seed", 109));
        itemsToLoot.add(new LootItem("Torstol seed", 108));
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
