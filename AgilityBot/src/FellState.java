import SharedBotLib.State;
import SharedBotLib.StateMachine;
import org.dreambot.api.methods.interactive.Players;

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
}
