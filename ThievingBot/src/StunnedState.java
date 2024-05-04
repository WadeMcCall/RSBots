import SharedBotLib.Utils;
import jdk.jshell.execution.Util;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.widgets.message.Message;

import java.util.Random;

public class StunnedState extends TheivingState {
    StunnedState(TheivingStateMachine sm) {
        super(sm);
    }

    Random rand = new Random();

    boolean openPouches;
    boolean eatFood;

    @Override
    public void doAction() {
        if (Inventory.contains("Coin pouch")) {
            if (openPouches) {
                Inventory.get("Coin pouch").interact();
                Sleep.sleep(50, 700);
                return;
            }
        }

        if (shouldEat()) {
            if(!Utils.Eat()) {
                state_machine.switchState(TheivingStateMachine.States.WALKING_TO_BANK_STATE);
                return;
            }
        }

        Sleep.sleepUntil(() -> Players.getLocal().getRenderableHeight() <= 250, 5000);
        state_machine.switchState(TheivingStateMachine.States.THEIVING_TARGET_STATE);
    }

    private boolean shouldEat() {
        int currentHealth = Utils.getCurrentHealthAsInt();

        if (currentHealth < state_machine.activity.npcTheivingHit + 5) {
            return true;
        }

        if (Skills.getRealLevel(Skill.HITPOINTS) - currentHealth > state_machine.activity.currentFoodItem.getHealAmount()) {
            return eatFood;
        }
        return false;
    }

    @Override
    public void Enter() {
        double rangeMin = 0.0;
        double rangeMax = 1.0;

        double rD = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
        openPouches = (rD >= .8) || (Inventory.contains("Coin Pouch") && Inventory.get("Coin pouch").getAmount() == 28);
        rD = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
        eatFood = rD >= .8;
    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {}
}
