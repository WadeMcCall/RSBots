import SharedBotLib.State;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.widgets.message.Message;

public class WalkingToTreesState extends State<WoodcuttingActivity> {

    WalkingToTreesState(WoodcuttingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (!Inventory.isFull() && !Players.getLocal().isAnimating() && state_machine.activity.treeArea.contains(Players.getLocal().getTile())) {
            state_machine.switchState(WoodcuttingStateMachine.States.FINDING_TREE_STATE);
            return;
        }
        if (GameObjects.closest(state_machine.activity.treeName).isOnScreen()) {
            state_machine.switchState(WoodcuttingStateMachine.States.FINDING_TREE_STATE);
            return;
        }
        if (!Players.getLocal().isMoving()|| Walking.getDestinationDistance() < 3) {
            Walking.walk(state_machine.activity.treeArea.getRandomTile());
            Sleep.sleepUntil(() -> GameObjects.closest(state_machine.activity.treeName).isOnScreen(), 2000);
            return;
        }

        if (Inventory.isFull()) {
            state_machine.switchState(WoodcuttingStateMachine.States.WALKING_TO_BANK_STATE);
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
