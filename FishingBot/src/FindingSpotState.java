import SharedBotLib.StateMachine;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.wrappers.widgets.message.Message;

public class FindingSpotState extends FishingState {
    FindingSpotState(FishingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Inventory.isFull()) {
            state_machine.switchState(FishingStateMachine.States.WALKING_TO_BANK_STATE);
            return;
        } else if (!Inventory.isFull() && Players.getLocal().isAnimating() && state_machine.activity.fishingArea.contains(Players.getLocal().getTile())) {
            state_machine.switchState(FishingStateMachine.States.FISHING_AT_SPOT_STATE);
            return;
        }
        if (!Players.getLocal().isAnimating() && !Players.getLocal().isMoving()) {
            NPC fishingSpot = NPCs.closest(n -> n != null && n.getName().contains("Fishing spot") && n.hasAction(state_machine.activity.actionName));
            if (fishingSpot != null && fishingSpot.interact(state_machine.activity.actionName)) {
                Sleep.sleepUntil(() -> Players.getLocal().isAnimating(), 1000);
            }
        }
    }

    @Override
    public void Enter() {}

    @Override
    public void Exit() {}

    @Override
    public void chatMessageRecieved(Message message) {

    }
}
