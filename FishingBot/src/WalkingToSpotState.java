import SharedBotLib.StateMachine;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.widgets.message.Message;

public class WalkingToSpotState extends FishingState{
    WalkingToSpotState(FishingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {

        if (state_machine.activity.fishingArea.contains(Players.getLocal().getTile())) {
            state_machine.switchState(FishingStateMachine.States.FINDING_SPOT_STATE);
            return;
        }

        if (!Players.getLocal().isMoving() || Walking.getDestinationDistance() <= 3) {
            Walking.walk(state_machine.activity.fishingArea.getRandomTile());
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
