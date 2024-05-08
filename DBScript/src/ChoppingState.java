import SharedBotLib.State;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.wrappers.widgets.message.Message;

public class ChoppingState extends State<WoodcuttingActivity> {
    ChoppingState(WoodcuttingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Inventory.isFull()) {
            state_machine.switchState(WoodcuttingStateMachine.States.WALKING_TO_BANK_STATE);
            return;
        }
        if (!Players.getLocal().isAnimating() && state_machine.activity.treeArea.contains(Players.getLocal().getTile())) {
            state_machine.switchState(WoodcuttingStateMachine.States.FINDING_TREE_STATE);
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
