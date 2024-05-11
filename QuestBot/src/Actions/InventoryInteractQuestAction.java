package Actions;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.utilities.Sleep;

import java.util.function.Predicate;

public class InventoryInteractQuestAction extends QuestAction {
    public String item;
    public String action;

    InventoryInteractQuestAction(String _item, String _action, Predicate<InventoryInteractQuestAction> completionCheck) {
        super(a -> completionCheck.test((InventoryInteractQuestAction) a));

        item = _item;
        action = _action;
    }

    @Override
    public ActionResult doAction() {
        Inventory.get(item).interact(action);
        Sleep.sleepUntil(() -> isComplete(), 10000);

        if (isComplete()) {
            return ActionResult.FINISH;
        }

        return ActionResult.CONTINUE;
    }
}
