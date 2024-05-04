import SharedBotLib.State;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;

public class WalkingToSpotState extends State<CookingActivity> {

    public WalkingToSpotState(CookingStateMachine cookingStateMachine) {
        super(cookingStateMachine);
    }

    @Override
    public void doAction() {

        if (state_machine.activity.cookingArea.contains(Players.getLocal().getTile())) {
            state_machine.switchState(CookingStateMachine.States.FINDING_SPOT);
            return;
        }

        if (!Players.getLocal().isMoving() || Walking.getDestinationDistance() <= 1) {
            Walking.walk(state_machine.activity.cookingArea.getRandomTile());
        }
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }
}
