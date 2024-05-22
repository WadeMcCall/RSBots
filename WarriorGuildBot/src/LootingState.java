import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.Utils;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.widgets.message.Message;

import java.util.Arrays;
import java.util.List;

public class LootingState extends State {
    public LootingState(StateMachine sm) {
        super(sm);
    }

    private final List<String> itemsToLoot = Arrays.asList("Adamant platebody", "Adamant platelegs", "Adamant full helm", "Warrior guild token");

    @Override
    public void doAction() {
        if (!isDoneLooting()) {
            lootItems();
            return;
        }

        if (Inventory.containsAll("Adamant platebody", "Adamant platelegs", "Adamant full helm") && shouldBank()) {
            state_machine.switchState(WarriorStateMachine.States.WALKING_TO_BANK);
            return;
        }

        if (Inventory.containsAll("Adamant platebody", "Adamant platelegs", "Adamant full helm")) {
            state_machine.switchState(WarriorStateMachine.States.SPAWNING_NEW_ARMOR);
            return;
        }
    }

    private boolean shouldBank() {
        return Inventory.count(x -> x.hasAction("Eat")) <= 4;
    }

    private void lootItems() {
        for (String item : itemsToLoot) {
            GroundItem itemToLoot = GroundItems.closest(item);
            if (itemToLoot == null)
                continue;

            if (Inventory.isFull())
                Utils.Eat();

            itemToLoot.interact("Take");
            Sleep.sleepUntil(() -> Inventory.contains(item), 1200);
        }
    }

    private boolean isDoneLooting() {
        return GroundItems.all(itemsToLoot.toArray(new String[0])).isEmpty();
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
