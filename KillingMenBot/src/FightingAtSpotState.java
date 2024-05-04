import SharedBotLib.Activity;
import SharedBotLib.Utils;
import org.dreambot.api.methods.combat.Combat;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.methods.container.impl.Inventory;

import java.util.Random;

public class FightingAtSpotState extends KillingState<Activity> {
    private int previousHealthPercent = 100;
    Random rand = new Random();

    FightingAtSpotState(FightingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (!Inventory.contains("Trout")) {
            state_machine.switchState(FightingStateMachine.States.WALKING_TO_BANK_STATE);
            return;
        } else if (!Players.getLocal().isInCombat()) {
            if (previousHealthPercent < 40) {
                if (!Utils.Eat()) {
                    state_machine.switchState(FightingStateMachine.States.WALKING_TO_BANK_STATE);
                    return;
                }
            }

            if (state_machine.activity.shouldLoot) {
                state_machine.switchState(FightingStateMachine.States.LOOTING_STATE);
            } else {
                state_machine.switchState(FightingStateMachine.States.FINDING_TARGET_STATE);
            }
            return;
        }

        if(Dialogues.canContinue()){
            Dialogues.continueDialogue();
        }

        int healthPercent = Combat.getHealthPercent();
        if (healthPercent != previousHealthPercent) {
            previousHealthPercent = healthPercent;
            healthChanged();
        }
    }

    private void healthChanged() {

        double rangeMin = 0.0;
        double rangeMax = 1.0;

        if (Utils.getCurrentHealthAsInt() <= (state_machine.activity.enemyMaxHit + 2)) {
            Utils.Eat();
            return;
        }
        if (Utils.getCurrentHealthAsInt() <= (state_machine.activity.enemyMaxHit * 2) + 2) {
            double rD = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
            if (rD > .7) {
                Utils.Eat();
                return;
            }
        }
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }
}
