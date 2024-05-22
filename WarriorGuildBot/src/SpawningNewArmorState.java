import SharedBotLib.State;
import SharedBotLib.StateMachine;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.widgets.message.Message;

public class SpawningNewArmorState extends State {
    public SpawningNewArmorState(StateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Players.getLocal().isInCombat()) {
            state_machine.switchState(WarriorStateMachine.States.KILLING_ANIMATED_ARMOR);
            return;
        }

        GameObjects.closest("Magical Animator").interact("Animate");
        Sleep.sleepUntil(() -> Players.getLocal().isInCombat(), 10000);
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
