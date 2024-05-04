import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.widgets.message.Message;

public class UseBankState extends TheivingState{
    UseBankState(TheivingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Inventory.contains("Coin pouch"))
            Inventory.get("Coin pouch").interact();

        if (Bank.isOpen()) {
            state_machine.switchState(TheivingStateMachine.States.BANKING_STATE);
            return;
        }

        if (!Bank.isOpen() || !Players.getLocal().isMoving()) {
            GameObject bankBooth = GameObjects.closest("Bank booth");
            bankBooth.interact("Bank");
            Sleep.sleepUntil(() -> Bank.isOpen(), 4000);
        }
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {}
}
