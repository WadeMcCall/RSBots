import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;

public class WalkingToSpotState extends MiningState {
    WalkingToSpotState(MiningStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (state_machine.activity.miningArea.contains(Players.getLocal().getTile())) {
            state_machine.switchState(MiningStateMachine.States.FINDING_SPOT_STATE);
            return;
        }

        if (!Players.getLocal().isMoving() || Walking.getDestinationDistance() <= 3) {
            Walking.walk(state_machine.activity.miningArea.getRandomTile());
        }
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }
}
