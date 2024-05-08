import SharedBotLib.Utils;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;

public class InteractQuestAction extends QuestAction{
    public String interactableName;
    public String interactAction;

    InteractQuestAction(String _name, String _action){
        interactableName = _name;
        interactAction = _action;
    }

    @Override
    public ActionResult doAction() {
        GameObject object = GameObjects.closest(interactableName);
        if (object == null || !object.hasAction(interactAction)) {
            return ActionResult.ERROR;
        }
        if (!object.interact(interactAction)) {
            Walking.walk(object.getTile());
            Sleep.sleepUntil(() -> !Players.getLocal().isMoving(), 3000);
            return ActionResult.CONTINUE;
        } else {
            Sleep.sleep((int) Utils.getRandomGuassianDistNotNegative(600, 300));
            return ActionResult.FINISH;
        }
    }
}
