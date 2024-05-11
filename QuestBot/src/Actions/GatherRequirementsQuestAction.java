package Actions;

import SharedBotLib.FoodItem;
import SharedBotLib.FoodService;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.utilities.Sleep;

import java.util.*;

public class GatherRequirementsQuestAction extends QuestAction {
    public Map<String, Integer> itemMap = new HashMap<>();
    public Map<String, Integer> conditionalItemMap = new HashMap<>();
    public boolean bringFood = false;

    GatherRequirementsQuestAction(Map<String, Integer> items) {
        itemMap.putAll(items);
    }

    GatherRequirementsQuestAction(Map<String, Integer> items, boolean _food) {
        itemMap.putAll(items);
        bringFood = _food;
    }

    GatherRequirementsQuestAction(Map<String, Integer> items, Map<String, Integer> conditionalItems) {
        itemMap.putAll(items);
        conditionalItemMap.putAll(conditionalItems);
    }

    GatherRequirementsQuestAction(Map<String, Integer> items, Map<String, Integer> conditionalItems, boolean _food) {
        itemMap.putAll(items);
        conditionalItemMap.putAll(conditionalItems);
        bringFood = _food;
    }


    @Override
    public ActionResult doAction() {
        // Check if inventory contains the required amounts
        boolean allItemsAcquired = itemMap.entrySet().stream()
                .allMatch(entry -> {
                    int count = Inventory.count(entry.getKey());
                    if (Equipment.contains(entry.getKey())) {
                        count++; // Increment count if item is equipped
                    }
                    return count >= entry.getValue();
                });

        if (allItemsAcquired)
            return ActionResult.FINISH;

        if (!Bank.isOpen()) {
            GameObjects.closest(x -> x.hasAction("Bank")).interact("Bank");
            Sleep.sleepUntil(() -> Bank.isOpen(), 10000);
        }
        for (Map.Entry<String, Integer> entry : itemMap.entrySet()) {
            String itemName = entry.getKey();
            int desiredAmount = entry.getValue();
            int currentAmount = Inventory.count(itemName);
            int amountToWithdraw = desiredAmount - currentAmount;

            if (amountToWithdraw > 0) {
                Bank.withdraw(itemName, amountToWithdraw);
            }
        }
        for (Map.Entry<String, Integer> entry : conditionalItemMap.entrySet()) {
            String itemName = entry.getKey();
            int desiredAmount = entry.getValue();
            int currentAmount = Inventory.count(itemName);
            int amountToWithdraw = desiredAmount - currentAmount;

            if (amountToWithdraw > 0) {
                if (Bank.contains(itemName))
                    Bank.withdraw(itemName, amountToWithdraw);
            }
        }

        if (bringFood) {
            Bson foodFilter = Filters.gte("healAmount", 3);
            List<FoodItem> foodItems = FoodService.getFoodItemsByFilter(foodFilter);

            foodItems.sort(Comparator.comparingInt(item -> item.getHealAmount()));

            for (FoodItem item : foodItems) {
                if (Bank.contains(item.getName())) {
                    Bank.withdrawAll(item.getName());
                    Sleep.sleepUntil(() -> Inventory.contains(item.getName()), 3000);
                    break;
                }
            }
        }
        return ActionResult.CONTINUE;
    }
}
