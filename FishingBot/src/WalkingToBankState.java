import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;

public class WalkingToBankState extends FishingState{
    WalkingToBankState(FishingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (state_machine.activity.bankArea.contains(Players.getLocal().getTile())){
            state_machine.switchState(FishingStateMachine.States.USE_BANK_STATE);
            return;
        }
        if (!Players.getLocal().isMoving() || Walking.getDestinationDistance() <= 3) {
            Walking.walk(state_machine.activity.bankArea.getRandomTile());
        }
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }
}
