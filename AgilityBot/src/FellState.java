import SharedBotLib.State;
import SharedBotLib.StateMachine;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.wrappers.widgets.message.Message;

public class FellState extends State<AgilityActivity> {
    public FellState(StateMachine<AgilityActivity> sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Players.getLocal().isHealthBarVisible()) {
            return;
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
