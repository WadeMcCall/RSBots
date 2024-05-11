package Quest.Actions;

import SharedBotLib.Utils;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.GroundItem;

import java.util.function.Predicate;

public class GroundItemInteractQuestAction extends QuestAction {
    public String interactableName;
    public String interactAction;

    public GroundItemInteractQuestAction(String _name, String _action){
        interactableName = _name;
        interactAction = _action;
    }

    public GroundItemInteractQuestAction(String _name, String _action, Predicate<GroundItemInteractQuestAction> completionCheck){
        super(a -> completionCheck.test((GroundItemInteractQuestAction) a));
        interactableName = _name;
        interactAction = _action;
    }

    @Override
    public ActionResult doAction() {
        GroundItem object = GroundItems.closest(interactableName);
        if (object == null || !object.hasAction(interactAction)) {
            return ActionResult.ERROR;
        }
        if (!object.interact(interactAction)) {
            Walking.walk(object.getTile());
            Sleep.sleepUntil(() -> !Players.getLocal().isMoving(), 3000);
            return ActionResult.CONTINUE;
        } else {
            Sleep.sleepUntil(() ->!Players.getLocal().isMoving(), 3000);
            Sleep.sleep((int) Utils.getRandomGuassianDistNotNegative(600, 300));
        }
        if (isComplete()) {
            return ActionResult.FINISH;
        }
        return ActionResult.CONTINUE;
    }
}
