import SharedBotLib.LootItem;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.widgets.message.Message;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LootingState extends KillingState<FightingActivity>{
    LootingState(FightingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Walking.getRunEnergy() > 50) {
            if (!Walking.isRunEnabled()) {
                Walking.toggleRun();
            }
        }

        if(Players.getLocal().isInCombat()) {
            state_machine.switchState(FightingStateMachine.States.FINDING_TARGET_STATE);
            return;
        }

        if (Players.getLocal().isMoving()) {
            return;
        }
        List<GroundItem> relevantGroundItems = GroundItems.all(groundItem -> state_machine.activity.itemsToLoot.stream()
                        .anyMatch(lootItem -> lootItem.getName().equals(groundItem.getName())));

        if (relevantGroundItems.isEmpty()) {
            state_machine.switchState(FightingStateMachine.States.FINDING_TARGET_STATE);
            return;
        }

        List<Item> inventoryItems = Inventory.all();

        List<LootItem> tempItemsToLoot = new ArrayList<>(state_machine.activity.itemsToLoot);
        tempItemsToLoot.add(new LootItem(state_machine.activity.currentFoodItem.getName(), state_machine.activity.foodPriority));

        // Find the LootItem with the lowest priority among those that have a matching Item in inventoryItems
        Optional<LootItem> lowestPriorityInventoryLootItem = tempItemsToLoot.stream()
                .filter(lootItem -> lootItem != null && lootItem.name != null && inventoryItems.stream().anyMatch(item -> item != null && item.getName() != null && item.getName().equals(lootItem.name)))
                .min(Comparator.comparingInt(lootItem -> lootItem.priority));

        // Find the LootItem with the highest priority among those that have a matching GroundItem in relevantGroundItems
        Optional<LootItem> highestPriorityGroundedLootItem = tempItemsToLoot.stream()
                .filter(lootItem -> lootItem != null && lootItem.name != null && relevantGroundItems.stream().anyMatch(item -> item != null && item.getName() != null && item.getName().equals(lootItem.name)))
                .max(Comparator.comparingInt(lootItem -> lootItem.priority));

        if (highestPriorityGroundedLootItem.isPresent()) {

            if (!Inventory.isFull()) {
                GroundItems.closest(highestPriorityGroundedLootItem.get().getName()).interact("Take");
                Sleep.sleep(50, 800);
                return;
            }
            if (lowestPriorityInventoryLootItem.isPresent()) {
                if(highestPriorityGroundedLootItem.get().priority > lowestPriorityInventoryLootItem.get().priority) {
                    if (Inventory.get(lowestPriorityInventoryLootItem.get().name).hasAction("Bury")) {
                        Inventory.get(lowestPriorityInventoryLootItem.get().name).interact("Bury");

                    } else if (Inventory.get(lowestPriorityInventoryLootItem.get().name).hasAction("Eat")) {
                        Inventory.get(lowestPriorityInventoryLootItem.get().name).interact("Eat");
                    } else {
                        Inventory.drop(lowestPriorityInventoryLootItem.get().name);
                    }
                    return;
                }
            }
        }

        state_machine.switchState(FightingStateMachine.States.FINDING_TARGET_STATE);
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
