import org.dreambot.api.methods.Animations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;

import SharedBotLib.Utils;
import org.dreambot.api.wrappers.widgets.message.Message;

public class TheivingTargetState extends TheivingState{
    TheivingTargetState(TheivingStateMachine sm) {
        super(sm);
    }
    NPC target;

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

        if (target != null) {
            target.interact("Pickpocket");
            Sleep.sleep(20,400);
        }
    }

    private boolean shouldEat() {
        int currentHealth = Utils.getCurrentHealthAsInt();
        return currentHealth < state_machine.activity.npcTheivingHit * 2;
    }

    private boolean isValidTarget() {
        return !target.isHealthBarVisible()
                && !target.isInCombat()
                && Map.canReach(target)
                && target != null;
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
