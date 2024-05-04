import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.methods.world.World;
import org.dreambot.api.methods.world.Worlds;
import org.dreambot.api.methods.worldhopper.WorldHopper;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;

public class FindingSpotState extends MiningState {
    FindingSpotState(MiningStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Inventory.isFull()) {
            state_machine.switchState(MiningStateMachine.States.WALKING_TO_BANK_STATE);
            return;
        } else if (!Inventory.isFull() && Players.getLocal().isAnimating() && state_machine.activity.miningArea.contains(Players.getLocal().getTile())) {
            state_machine.switchState(MiningStateMachine.States.MINING_AT_SPOT_STATE);
            return;
        }

        if (Walking.getRunEnergy() > 50 && !Walking.isRunEnabled()) {
            Walking.toggleRun();
        }

        if (!Players.getLocal().isAnimating() && !Players.getLocal().isMoving()) {
            GameObject miningSpot = GameObjects.closest(n -> n != null
                    && n.getName().contains(state_machine.activity.rockName)
                    && n.hasAction("Mine")
                    && !SharedBotLib.Utils.checkIfTileAroundHasPlayer(n));
            if (miningSpot != null && miningSpot.interact("Mine")) {
                Sleep.sleepUntil(() -> Players.getLocal().isAnimating(), 2000);
            } else {
                World world = Worlds.getRandomWorld(w -> !w.isPVP() && w.getMinimumLevel() == 0);
                WorldHopper.hopWorld(world);
                Sleep.sleep(5000, 10000);
            }
        }
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }
}
