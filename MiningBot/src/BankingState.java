import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.utilities.Sleep;

public class BankingState extends MiningState{
    BankingState(MiningStateMachine sm) {
        super(sm);
    }
    private final String[] ores = {"Iron ore", "Uncut sapphire", "Uncut ruby", "Uncut emerald", "Uncut diamond"};


    @Override
    public void doAction() {

        if (!Inventory.contains(ores)) {
            state_machine.switchState(MiningStateMachine.States.WALKING_TO_SPOT_STATE);
            return;
        }

        Bank.depositAllItems();

        Sleep.sleepUntil(() -> (!Inventory.contains(ores)), 2000);
        if (!Inventory.contains(ores)) {
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
}
