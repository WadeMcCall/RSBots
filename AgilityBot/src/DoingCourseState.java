import SharedBotLib.State;
import SharedBotLib.StateMachine;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.widgets.message.Message;

public class DoingCourseState extends State<AgilityActivity> {
    public DoingCourseState(StateMachine<AgilityActivity> sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Players.getLocal().isHealthBarVisible()) {
            state_machine.switchState(AgilityStateMachine.States.FELL);
            return;
        }
        if (Players.getLocal().isAnimating()
                || Players.getLocal().isMoving()
                || Players.getLocal().getAnimation() != -1
                || !Players.getLocal().canReach(Players.getLocal().getTile())) {
            Sleep.sleepUntil(() -> !Players.getLocal().isAnimating(), 4500);
            return;
        }

        state_machine.switchState(AgilityStateMachine.States.FINDING_OBSTACLE);
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
