import SharedBotLib.Activity;
import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.UserAreas;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.widgets.message.Message;

public class LeavingHugeEssMineState extends State<Activity> {
    public LeavingHugeEssMineState(StateMachine<Activity> sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        GameObject portal = GameObjects.closest("Portal");
        portal.interact();

        Sleep.sleepUntil(() -> !UserAreas.HugeGuardianRemains.contains(Players.getLocal()), 10000);
        state_machine.switchState(GuardiansStateMachine.States.FINDING_GUARDIAN);
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
