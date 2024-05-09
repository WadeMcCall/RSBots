import SharedBotLib.FoodItem;
import SharedBotLib.FoodService;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.utilities.Sleep;

import java.util.*;

public class GatherRequirementsQuestAction extends QuestAction {
    public Map<String, Integer> itemMap = new HashMap<>();
    public boolean bringFood = false;

    GatherRequirementsQuestAction(Map<String, Integer> items) {
        itemMap.putAll(items);
    }

    GatherRequirementsQuestAction(Map<String, Integer> items, boolean _food) {
        itemMap.putAll(items);
        bringFood = _food;
    }


    @Override
    public ActionResult doAction() {
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


        // Check if inventory contains the required amounts
        boolean allItemsAcquired = itemMap.entrySet().stream()
                .allMatch(entry -> Inventory.count(entry.getKey()) >= entry.getValue());

        if (allItemsAcquired)
            return ActionResult.FINISH;
        return ActionResult.CONTINUE;
    }
}
