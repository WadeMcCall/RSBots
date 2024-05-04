import SharedBotLib.State;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;

public class CookingAtSpotState extends State<CookingActivity> {
    private final String[] rawFood = {"Raw shrimps", "Raw anchovies", "Raw sardine", "Raw herring"};

    public CookingAtSpotState(CookingStateMachine cookingStateMachine) {
        super(cookingStateMachine);
    }

    @Override
    public void doAction() {
        if(!Inventory.contains(rawFood)) {
            state_machine.switchState(CookingStateMachine.States.WALKING_TO_BANK);
        } else if (!Players.getLocal().isAnimating()) {
            state_machine.switchState(CookingStateMachine.States.FINDING_SPOT);
        }
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }
}
