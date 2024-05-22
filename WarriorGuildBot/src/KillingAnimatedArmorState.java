import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.Utils;
import org.dreambot.api.methods.combat.Combat;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.widgets.message.Message;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class KillingAnimatedArmorState extends State {
    public KillingAnimatedArmorState(StateMachine sm) {
        super(sm);
    }

    final int enemyMaxHit = 12;

    private int previousHealthPercent = 100;
    Random rand = new Random();

    @Override
    public void doAction() {
        if (!Players.getLocal().isInCombat()) {
            if (previousHealthPercent < 40) {
                if (!Utils.Eat()) {
                    state_machine.switchState(WarriorStateMachine.States.WALKING_TO_BANK);
                    return;
                }
            }
            sipIfShould();
            state_machine.switchState(WarriorStateMachine.States.LOOTING);
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

    private void sipIfShould() {
        if (Skills.getBoostedLevel(Skill.STRENGTH) - Skills.getRealLevel(Skill.STRENGTH) <= 3) {
            Item strengthPot = Inventory.get(x -> x.getName().contains("Strength"));
            if (strengthPot != null) {
                strengthPot.interact("Drink");
                Sleep.sleep((int)Utils.getRandomGuassianDistNotNegative(1200, 300));
            }
        }
        if (Skills.getBoostedLevel(Skill.ATTACK) - Skills.getRealLevel(Skill.ATTACK) <= 3) {
            Item attackPot = Inventory.get(x -> x.getName().contains("Attack"));
            if (attackPot != null) {
                attackPot.interact("Drink");
                Sleep.sleep((int)Utils.getRandomGuassianDistNotNegative(1200, 300));
            }
        }
        if (Skills.getBoostedLevel(Skill.DEFENCE) - Skills.getRealLevel(Skill.DEFENCE) <= 3) {
            Item defencePot = Inventory.get(x -> x.getName().contains("Defence"));
            if (defencePot != null) {
                defencePot.interact("Drink");
                Sleep.sleep((int)Utils.getRandomGuassianDistNotNegative(1200, 300));
            }
        }
    }


    private void sipPotions() {
        List<String> potions = Arrays.asList("Strength", "Attack", "Defence");

        for (String potion : potions) {
            Item pot = Inventory.get(x -> x.getName().contains(potion));
            if (pot == null)
                continue;

            pot.interact("Drink");
            Sleep.sleep((int)Utils.getRandomGuassianDistNotNegative(1500, 300));
        }
    }


    private boolean shouldSipPotions() {
        return Skills.getBoostedLevel(Skill.STRENGTH) - Skills.getRealLevel(Skill.STRENGTH) <= 5;
    }

    private void healthChanged() {

        double rangeMin = 0.0;
        double rangeMax = 1.0;

        if (Utils.getCurrentHealthAsInt() <= (enemyMaxHit + 2)) {
            Utils.Eat();
            sipIfShould();
            return;
        }
        if (Utils.getCurrentHealthAsInt() <= Skills.getRealLevel(Skill.HITPOINTS) - 15) {
            double rD = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
            if (rD > .7) {
                Utils.Eat();
                sipIfShould();
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

    @Override
    public void chatMessageRecieved(Message message) {

    }
}
