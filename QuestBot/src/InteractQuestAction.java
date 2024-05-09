import SharedBotLib.Utils;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;

import java.util.function.Predicate;

public class InteractQuestAction extends QuestAction{
    public String interactableName;
    public String interactAction;
    public int interactableID = -1;

    public InteractQuestAction(String _name, String _action){
        interactableName = _name;
        interactAction = _action;
    }

    public InteractQuestAction(int _id, String _action) {
        interactableID = _id;
        interactAction = _action;
    }

    public InteractQuestAction(String _name, String _action, Predicate<InteractQuestAction> completionCheck){
        super(a -> completionCheck.test((InteractQuestAction) a));
        interactableName = _name;
        interactAction = _action;
    }

    public InteractQuestAction(int _id, String _action, Predicate<InteractQuestAction> completionCheck) {
        super(a -> completionCheck.test((InteractQuestAction) a));
        interactableID = _id;
        interactAction = _action;
    }

    @Override
    public ActionResult doAction() {
        if (Dialogues.inDialogue())
            Dialogues.continueDialogue();

        GameObject object = null;
        if(interactableName != null)
            object = GameObjects.closest(interactableName);
        else if (interactableID != -1) {
            object = GameObjects.closest(interactableID);
        }
        if (object == null || !object.hasAction(interactAction)) {
            return ActionResult.ERROR;
        }
        if (!object.interact(interactAction)) {
            Walking.walk(object.getTile());
            Sleep.sleepUntil(() -> !Players.getLocal().isMoving(), 3000);
            return ActionResult.CONTINUE;
        } else {
            Sleep.sleepUntil(() -> isComplete(), 10000);
            Sleep.sleep((int) Utils.getRandomGuassianDistNotNegative(600, 100));
        }
        if (isComplete()) {
            return ActionResult.FINISH;
        }
        return ActionResult.CONTINUE;
    }
}
