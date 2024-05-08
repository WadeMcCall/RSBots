import SharedBotLib.State;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.widgets.message.Message;

public class WalkingToBankState extends State<WoodcuttingActivity> {

    WalkingToBankState(WoodcuttingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (state_machine.activity.bankArea.contains(Players.getLocal().getTile()) ||
                GameObjects.closest("Bank booth").isOnScreen()){
            state_machine.switchState(WoodcuttingStateMachine.States.USE_BANK_STATE);
        }
        if (!Players.getLocal().isMoving() || Walking.getDestinationDistance() <= 1) {
            Walking.walk(state_machine.activity.bankArea.getRandomTile());
            Sleep.sleepUntil(() -> Players.getLocal().isMoving(), 2000);
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
