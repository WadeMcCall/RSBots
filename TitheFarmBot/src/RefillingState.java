import SharedBotLib.State;
import SharedBotLib.Utils;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.widgets.message.Message;

/*
    This state refills all the watering cans until they are all full.
*/

public class RefillingState extends State {
    public GameObject waterBarrel;

    public RefillingState(FarmingStateMachine farmingStateMachine) {
        super(farmingStateMachine);
    }

    @Override
    public void doAction() {
        if (!Inventory.contains("Watering can")) {
            state_machine.switchState(FarmingStateMachine.States.WALKING_TO_START_POSITION);
            return;
        }
        Item wateringCan = Inventory.get("Watering can");
        if (waterBarrel == null) {
            Walking.walk(new Tile(1807, 3501));
            Sleep.sleepUntil(() -> !Players.getLocal().isMoving(), 10000);
            waterBarrel = GameObjects.closest("Water Barrel");
        }

        wateringCan.useOn(waterBarrel);
        Sleep.sleep((int) Utils.getRandomGuassianDistNotNegative(500, 150));
    }

    @Override
    public void Enter() {
        waterBarrel = GameObjects.closest("Water Barrel");
    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {

    }
}
