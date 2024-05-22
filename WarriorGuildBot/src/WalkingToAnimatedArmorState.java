import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.UserAreaService;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.widgets.message.Message;

public class WalkingToAnimatedArmorState extends State {
    public WalkingToAnimatedArmorState(StateMachine sm) {
        super(sm);

        animatedArea = UserAreaService.getAreaByName("WarriorsGuildArmorRoom");
    }

    Area animatedArea;

    @Override
    public void doAction() {
        if (animatedArea.contains(Players.getLocal())) {
            state_machine.switchState(WarriorStateMachine.States.SPAWNING_NEW_ARMOR);
            return;
        }

        if (Players.getLocal().isMoving())
            return;

        Walking.walk(animatedArea.getRandomTile());
        Sleep.sleepUntil(() -> animatedArea.contains(Players.getLocal()), 10000);
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
