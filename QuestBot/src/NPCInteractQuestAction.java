import SharedBotLib.Utils;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;

import java.util.function.Predicate;

public class NPCInteractQuestAction extends QuestAction{
    public String interactableName;
    public String interactAction;

    public NPCInteractQuestAction(String _name, String _action){
        interactableName = _name;
        interactAction = _action;
    }

    public NPCInteractQuestAction(String _name, String _action, Predicate<NPCInteractQuestAction> completionCheck){
        super(a -> completionCheck.test((NPCInteractQuestAction) a));
        interactableName = _name;
        interactAction = _action;
    }

    @Override
    public ActionResult doAction() {
        NPC object = NPCs.closest(interactableName);
        if (object == null || !object.hasAction(interactAction)) {
            return ActionResult.ERROR;
        }
        if (!object.interact(interactAction)) {
            Walking.walk(object.getTile());
            Sleep.sleepUntil(() -> !Players.getLocal().isMoving(), 10000);
            return ActionResult.CONTINUE;
        } else {
            Sleep.sleepUntil(() ->!Players.getLocal().isMoving(), 10000);
            Sleep.sleep((int) Utils.getRandomGuassianDistNotNegative(1200, 100));
        }
        if (isComplete()) {
            return ActionResult.FINISH;
        }
        return ActionResult.CONTINUE;
    }
}
