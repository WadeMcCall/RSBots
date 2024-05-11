package Actions;

import SharedBotLib.Utils;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.utilities.Sleep;

import java.util.function.Predicate;

public class UseItemQuestAction extends QuestAction {
    private String item1;
    private String item2;

    public UseItemQuestAction(String _item1, String _item2) {
        item1 = _item1;
        item2 = _item2;
    }


    public UseItemQuestAction(String _item1, String _item2, Predicate<UseItemQuestAction> completionCheck) {
        super(a -> completionCheck.test((UseItemQuestAction) a));
        item1 = _item1;
        item2 = _item2;
    }


    @Override
    public ActionResult doAction() {
        if (!Inventory.contains(item1) || !Inventory.contains(item2)) {
            return ActionResult.ERROR;
        }
        Inventory.get(item1).useOn(item2);
        Sleep.sleep((int) Utils.getRandomGuassianDistNotNegative(600, 300));

        if (isComplete()) {
            return ActionResult.FINISH;
        }

        return ActionResult.CONTINUE;
    }
}
