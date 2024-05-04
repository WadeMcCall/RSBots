import SharedBotLib.Activity;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.widgets.message.Message;

public class UseBankState extends KillingState<Activity> {
    UseBankState(FightingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {

        if (Bank.isOpen()) {
            state_machine.switchState(FightingStateMachine.States.BANKING_STATE);
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
    public void chatMessageRecieved(Message message) {

    }
}
