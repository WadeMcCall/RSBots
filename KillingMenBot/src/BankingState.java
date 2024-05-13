import SharedBotLib.FoodItem;
import SharedBotLib.FoodService;
import SharedBotLib.Utils;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.widgets.message.Message;

import java.util.Comparator;
import java.util.List;

public class BankingState extends KillingState<FightingActivity> {
    BankingState(FightingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (state_machine.activity.currentFoodItem != null) {
            if (Inventory.contains(state_machine.activity.currentFoodItem.getName())) {
                state_machine.switchState(FightingStateMachine.States.WALKING_TO_SPOT_STATE);
                return;
            }
        }

        if (!Inventory.isEmpty()) {
            Bank.depositAllItems();
            return;
        }

        Bson foodFilter = Filters.gt("healAmount", state_machine.activity.minHealingFood);
        List<FoodItem> foodItems = FoodService.getFoodItemsByFilter(foodFilter);

        foodItems.sort(Comparator.comparingInt(item -> item.getHealAmount()));

        for (FoodItem item : foodItems) {
            if (Bank.contains(item.getName())) {
                Bank.withdrawAll(item.getName());
                state_machine.activity.currentFoodItem = item;
                Sleep.sleepUntil(() -> Inventory.contains(state_machine.activity.currentFoodItem.getName()), 3000);
                break;
            }
        }

        if (state_machine.activity.currentFoodItem == null) {
            ScriptManager.getScriptManager().stop();
        }

        if (Inventory.contains(state_machine.activity.currentFoodItem.getName())) {
            Sleep.sleep((int) Utils.getRandomGuassianDistNotNegative(300,40));
            Bank.close();
        }
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {

    }
}
