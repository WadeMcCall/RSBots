import SharedBotLib.State;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.widgets.message.Message;

public class WalkingToStartPositionState extends State {
    private static final Tile START_POSITION = new Tile(1813, 3514);

    public WalkingToStartPositionState(FarmingStateMachine farmingStateMachine) {
        super(farmingStateMachine);
    }

    @Override
    public void doAction() {
        if (Players.getLocal().getTile() == START_POSITION) {
            state_machine.switchState(FarmingStateMachine.States.PLANTING);
            return;
        }

        Walking.walkExact(START_POSITION);
        Sleep.sleepUntil(() -> Players.getLocal().getTile() == START_POSITION || !Players.getLocal().isMoving(), 20000);
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
