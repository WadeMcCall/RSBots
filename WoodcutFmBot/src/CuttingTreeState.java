import SharedBotLib.Activity;
import SharedBotLib.State;
import SharedBotLib.StateMachine;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.wrappers.widgets.message.Message;

public class CuttingTreeState extends State<Activity> {
    public CuttingTreeState(StateMachine<Activity> sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Inventory.isFull()) {
            state_machine.switchState(WoodcutFMStateMachine.States.FIREMAKING);
            return;
        }
        if (!Players.getLocal().isAnimating()) {
            state_machine.switchState(WoodcutFMStateMachine.States.FINDING_TREE);
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
