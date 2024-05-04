import SharedBotLib.State;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;

public class WalkingToBankState extends State<CookingActivity> {
    public WalkingToBankState(CookingStateMachine cookingStateMachine) {
        super(cookingStateMachine);
    }

    @Override
    public void doAction() {
        if (state_machine.activity.bankArea.contains(Players.getLocal().getTile())){
            state_machine.switchState(CookingStateMachine.States.USE_BANK);
            return;
        }
        if (!Players.getLocal().isMoving()) {
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
