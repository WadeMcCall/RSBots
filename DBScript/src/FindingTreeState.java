import SharedBotLib.State;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.widgets.message.Message;

public class FindingTreeState extends State<WoodcuttingActivity> {


    FindingTreeState(WoodcuttingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Inventory.isFull() && !state_machine.activity.bankArea.contains(Players.getLocal().getTile())) {
            state_machine.switchState(WoodcuttingStateMachine.States.WALKING_TO_BANK_STATE);
            return;
        } else if (!Inventory.isFull() && Players.getLocal().isAnimating() && state_machine.activity.treeArea.contains(Players.getLocal().getTile())) {
            state_machine.switchState(WoodcuttingStateMachine.States.CHOPPING_STATE);
            return;
        }
        if (!Players.getLocal().isAnimating() && !Players.getLocal().isMoving()) {
            GameObject tree = GameObjects.closest(t -> t.getName().equalsIgnoreCase(state_machine.activity.treeName) && state_machine.activity.treeArea.contains(t.getTile()));
            if (tree != null && tree.interact()) {
                Sleep.sleepUntil(() -> Players.getLocal().isAnimating(), 7000);
            } else {
                Walking.walk(state_machine.activity.treeArea.getRandomTile());
            }
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
