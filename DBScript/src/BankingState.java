import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.utilities.Sleep;

import SharedBotLib.State;
import org.dreambot.api.wrappers.widgets.message.Message;

public class BankingState extends State<WoodcuttingActivity> {

    BankingState(WoodcuttingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (!Inventory.isFull() && !state_machine.activity.treeArea.contains(Players.getLocal().getTile())) {
            state_machine.switchState(WoodcuttingStateMachine.States.WALKING_TO_TREES_STATE);
        }

        Bank.depositAllItems();
        Sleep.sleepUntil(() -> !Inventory.contains(state_machine.activity.logName), 2000);
        if (!Inventory.contains(state_machine.activity.logName)) {
            Sleep.sleep(300);
            Bank.close();
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
