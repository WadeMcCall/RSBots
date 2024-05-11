package Actions;

import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;

import java.util.function.Predicate;

public class WalkExactTileQuestAction extends QuestAction {
    public Tile walkToTile;

    public WalkExactTileQuestAction(Tile _tile) {
        walkToTile = _tile;
    }

    public WalkExactTileQuestAction(Tile _tile, Predicate<WalkQuestAction> completionCheck) {
        super(a -> completionCheck.test((WalkQuestAction) a));
        walkToTile = _tile;
    }

    @Override
    public ActionResult doAction() {
        if (walkToTile.equals(Players.getLocal().getTile()))
            return ActionResult.FINISH;

        if (Walking.getDestinationDistance() <= 5 || !Players.getLocal().isMoving()) {
            Walking.walk(walkToTile);
            Sleep.sleepUntil(()->Walking.getDestinationDistance() <= 5, 3000);
        }

        return ActionResult.CONTINUE;
    }
}
