import SharedBotLib.Activity;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.widgets.message.Message;

public class WalkingToSpotState extends KillingState<Activity> {
    WalkingToSpotState(FightingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {

        if (state_machine.activity.fightingArea.contains(Players.getLocal().getTile())) {
            state_machine.switchState(FightingStateMachine.States.FINDING_TARGET_STATE);
            return;
        }

        if (!Players.getLocal().isMoving() || Walking.getDestinationDistance() <= 3) {
            Walking.walk(state_machine.activity.fightingArea.getRandomTile());
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
