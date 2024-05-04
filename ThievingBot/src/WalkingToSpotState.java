import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.widgets.message.Message;

public class WalkingToSpotState extends TheivingState{
    WalkingToSpotState(TheivingStateMachine sm) {
        super(sm);
    }


    @Override
    public void doAction() {

        if (state_machine.activity.currentFoodItem == null) {
            state_machine.switchState(TheivingStateMachine.States.WALKING_TO_BANK_STATE);
            return;
        }

        if (state_machine.activity.theivingArea.contains(Players.getLocal().getTile())) {
            state_machine.switchState(TheivingStateMachine.States.THEIVING_TARGET_STATE);
            return;
        }

        if (!Players.getLocal().isMoving() || Walking.getDestinationDistance() <= 3) {
            Walking.walk(state_machine.activity.theivingArea.getRandomTile());
        }
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {}
}
