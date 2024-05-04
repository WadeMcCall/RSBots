import SharedBotLib.State;
import org.dreambot.api.input.Keyboard;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;

public class FindingSpotState extends State<CookingActivity> {
    private final String[] rawFood = {"Raw shrimps", "Raw anchovies", "Raw sardine", "Raw herring"};

    public FindingSpotState(CookingStateMachine cookingStateMachine) {
        super(cookingStateMachine);
    }

    @Override
    public void doAction() {
        if(!Inventory.contains(rawFood)) {
            state_machine.switchState(CookingStateMachine.States.WALKING_TO_BANK);
        }

        if (!Players.getLocal().isAnimating() && !Players.getLocal().isMoving()) {
            GameObject range = GameObjects.closest(gameObject -> gameObject != null && gameObject.getName().equals("Cooking range"));
            if (range != null && range.interact("Cook")) {
                Sleep.sleep(400, 1000);
                Keyboard.type(" ", false);
            }
        }

        Sleep.sleepUntil(() -> Players.getLocal().isAnimating(), 2000);
        if (Players.getLocal().isAnimating()) {
            state_machine.switchState(CookingStateMachine.States.COOKING);
        }
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }
}
