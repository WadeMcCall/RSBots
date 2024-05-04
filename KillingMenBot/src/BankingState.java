import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.utilities.Sleep;

public class BankingState extends KillingState<FightingActivity> {
    BankingState(FightingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Inventory.isFull()) {
            state_machine.switchState(FightingStateMachine.States.WALKING_TO_SPOT_STATE);
            return;
        }

        if (!Inventory.isEmpty()) {
            Bank.depositAllItems();
            return;
        }

        Bank.withdrawAll("Trout");
        if (Inventory.contains("Trout")) {
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
