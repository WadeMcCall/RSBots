import org.dreambot.api.methods.Animations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.grandexchange.GrandExchange;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;

import SharedBotLib.Utils;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.widgets.message.Message;

import java.util.Arrays;
import java.util.List;

public class TheivingTargetState extends TheivingState{
    TheivingTargetState(TheivingStateMachine sm) {
        super(sm);
    }
    NPC target;
    List<String> itemWhitelist = Arrays.asList("Ranarr seed", "Snape grass seed", "Snapdragon seed", "Cadantine seed", "Torstol seed", "Avantoe seed");

    @Override
    public void doAction() {
        if (state_machine.activity.currentFoodItem == null) {
            state_machine.switchState(TheivingStateMachine.States.WALKING_TO_BANK_STATE);
            return;
        }
        if (!Inventory.contains(state_machine.activity.currentFoodItem.getName())) {
            state_machine.switchState(TheivingStateMachine.States.WALKING_TO_BANK_STATE);
            return;
        }

        if (Players.getLocal().getRenderableHeight() > 250) {
            state_machine.switchState(TheivingStateMachine.States.STUNNED_STATE);
            return;
        }

        if (Inventory.contains("Coin pouch")) {
            if (Inventory.get("Coin pouch").getAmount() == 28) {
                Inventory.get("Coin pouch").interact();
                return;
            }
        }

        if (shouldEat()) {
            if(!Utils.Eat()) {
                state_machine.switchState(TheivingStateMachine.States.WALKING_TO_BANK_STATE);
            }
        }

        if (Inventory.isItemSelected())
            Inventory.deselect();

        if (!isValidTarget())
            getTarget();

        if (Inventory.isFull() && state_machine.activity.numItemsFromTarget > 20) {
            // Drop the least valuable item stacks
            for (Item item : Inventory.all()) {
                if (item.hasAction("Eat"))
                    continue;
                if (itemWhitelist.contains(item.getName()))
                    continue;
                item.interact("Drop");
            }
        }

        if (target != null) {
            target.interact("Pickpocket");
            double delay = Utils.getRandomGuassianDistNotNegative(300, 150);
            Sleep.sleep((int)delay);
        } else {
            target = NPCs.closest(state_machine.activity.npcName);
        }
    }

    private boolean shouldEat() {
        int currentHealth = Utils.getCurrentHealthAsInt();
        return currentHealth < state_machine.activity.npcTheivingHit * 2;
    }

    private boolean isValidTarget() {
        return target != null
                && !target.isHealthBarVisible()
                && !target.isInCombat()
                && Map.canReach(target);
    }

    private NPC getTarget() {
        return NPCs.closest(n -> n.getName().contains(state_machine.activity.npcName)
                && !n.isHealthBarVisible()
                && !n.isInCombat()
                && Map.canReach(n));
    }

    @Override
    public void Enter() {
        target = getTarget();
    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {}
}
