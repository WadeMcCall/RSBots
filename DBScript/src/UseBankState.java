import SharedBotLib.State;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.widgets.message.Message;

public class UseBankState extends State<WoodcuttingActivity> {

    UseBankState(WoodcuttingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Bank.isOpen() && Inventory.isFull()) {
            state_machine.switchState(WoodcuttingStateMachine.States.BANKING_STATE);
        }

        if (!Bank.isOpen() || !Players.getLocal().isMoving()) {
            GameObject bankBooth = GameObjects.closest(b -> b.getName() == "Bank booth" && state_machine.activity.bankArea.contains((b)));
            bankBooth.interact("Bank");
            Sleep.sleepUntil(() -> Bank.isOpen(), 4000);
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
