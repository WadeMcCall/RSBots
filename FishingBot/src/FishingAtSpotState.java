import SharedBotLib.StateMachine;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.wrappers.widgets.message.Message;

public class FishingAtSpotState extends FishingState {
    FishingAtSpotState(FishingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Inventory.isFull()) {
            state_machine.switchState(FishingStateMachine.States.WALKING_TO_BANK_STATE);
            return;
        }
        if (!Players.getLocal().isAnimating() && state_machine.activity.fishingArea.contains(Players.getLocal().getTile())) {
            state_machine.switchState(FishingStateMachine.States.FINDING_SPOT_STATE);
        }
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {

    }
}
