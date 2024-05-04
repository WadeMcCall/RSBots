import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;

public class MiningAtSpotState extends MiningState{
    MiningAtSpotState(MiningStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Inventory.isFull()) {
            state_machine.switchState(MiningStateMachine.States.WALKING_TO_BANK_STATE);
            return;
        }
        if (!Players.getLocal().isAnimating() && state_machine.activity.miningArea.contains(Players.getLocal().getTile())) {
            state_machine.switchState(MiningStateMachine.States.FINDING_SPOT_STATE);
        }
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }
}
