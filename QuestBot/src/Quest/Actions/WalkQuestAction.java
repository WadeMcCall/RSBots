package Quest.Actions;

import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;

import java.util.function.Predicate;

public class WalkQuestAction extends QuestAction {
    public Area walkToArea;

    public WalkQuestAction(Area _area) {
        walkToArea = _area;
    }

    public WalkQuestAction(Area _area, Predicate<WalkQuestAction> completionCheck) {
        super(a -> completionCheck.test((WalkQuestAction) a));
        walkToArea = _area;
    }

    @Override
    public ActionResult doAction() {
        if (walkToArea.contains(Players.getLocal()))
            return ActionResult.FINISH;

        if (Walking.getDestinationDistance() <= 3 || !Players.getLocal().isMoving()) {
            Walking.walk(walkToArea);
            Sleep.sleepUntil(()->Walking.getDestinationDistance() <= 3, 3000);
        }

        return ActionResult.CONTINUE;
    }
}
