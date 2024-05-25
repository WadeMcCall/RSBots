import SharedBotLib.State;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.widgets.message.Message;

public class TurningInState extends State {
    public GameObject sack;
    public TurningInState(FarmingStateMachine farmingStateMachine) {
        super(farmingStateMachine);
    }

    @Override
    public void doAction() {
        if (sack == null) {
            sack = GameObjects.closest("Sack");
        }

        if (!Inventory.contains(x -> x.getName().contains("fruit"))) {
            state_machine.switchState(FarmingStateMachine.States.REFILLING);
            return;
        }

        sack.interact("Deposit");
        Sleep.sleepUntil(() -> !Inventory.contains(x -> x.getName().contains("fruit")), 10000);
    }

    @Override
    public void Enter() {
        sack = GameObjects.closest("Sack");
    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {

    }
}
