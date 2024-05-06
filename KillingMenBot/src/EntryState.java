import SharedBotLib.FoodItem;
import SharedBotLib.FoodService;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.wrappers.widgets.message.Message;

import java.util.List;

public class EntryState extends KillingState<FightingActivity>{
    EntryState(FightingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        List<FoodItem> foods = FoodService.getAllFoodItems();

        for (FoodItem food : foods) {
            if (Inventory.contains(food.getName())) {
                Logger.log(food.getName());
                state_machine.activity.currentFoodItem = food;
                break;
            }
        }

        if (state_machine.activity.currentFoodItem != null) {
            state_machine.switchState(FightingStateMachine.States.WALKING_TO_SPOT_STATE);
            return;

        }
        state_machine.switchState(FightingStateMachine.States.WALKING_TO_BANK_STATE);
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
