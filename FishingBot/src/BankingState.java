import SharedBotLib.StateMachine;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.widgets.message.Message;

public class BankingState extends FishingState {
    private final String[] fishes = {"Raw Shrimps", "Raw Anchovies", "Raw sardine", "Raw herring"};

    BankingState(FishingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (!Inventory.contains(fishes)) {
            state_machine.switchState(FishingStateMachine.States.WALKING_TO_SPOT_STATE);
            return;
        }

        for (String fish : fishes) {
            Bank.depositAll(fish);
        }

        Sleep.sleepUntil(() -> (!Inventory.contains(fishes)), 2000);
        if (!Inventory.contains(fishes)) {
            Sleep.sleep(300);
            Bank.close();
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
