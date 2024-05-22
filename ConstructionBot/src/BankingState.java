import SharedBotLib.State;
import SharedBotLib.StateMachine;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.widgets.message.Message;

public class BankingState extends State<ConstructionActivity> {
    public BankingState(StateMachine<ConstructionActivity> sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        state_machine.activity.invSetup.getLoadoutFromBank();
        Sleep.sleepUntil(() -> state_machine.activity.invSetup.hasFullLoadout(), 20000);
        Bank.close();
        if (!state_machine.activity.invSetup.hasFullLoadout()) {
            state_machine.activity.invSetup.getLoadoutFromBank();
            Sleep.sleepUntil(() -> state_machine.activity.invSetup.hasFullLoadout(), 20000);
            if (!state_machine.activity.invSetup.hasFullLoadout()) {
                ScriptManager.getScriptManager().stop();
                return;
            }
        }
        state_machine.switchState(ConstructionStateMachine.States.GOING_TO_HOUSE);
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
