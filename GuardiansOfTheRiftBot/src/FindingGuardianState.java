import SharedBotLib.Activity;
import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.UserAreas;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.quest.Quests;
import org.dreambot.api.methods.quest.book.PaidQuest;
import org.dreambot.api.methods.quest.book.Quest;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.api.wrappers.widgets.WidgetChild;
import org.dreambot.api.wrappers.widgets.message.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FindingGuardianState extends State<Activity> {
    public FindingGuardianState(StateMachine<Activity> sm) {
        super(sm);
    }

    private int runecraftingLevel;
    Random rand = new Random();

    @Override
    public void doAction() {
        Player me = Players.getLocal();

        if (Dialogues.canContinue())
            Dialogues.continueDialogue();

        if(!UserAreas.GuardiansFullArea.contains(me)) {
            state_machine.switchState(GuardiansStateMachine.States.CRAFTING_RUNES);
            Sleep.sleep(600,1200);
            return;
        }

        if (Walking.getDestinationDistance() >= 7)
            return;

        GameObject target = getGuardianTarget();

        if (target == null || !target.interact()) {
            if(!UserAreas.GuardiansFullArea.contains(me)) {
                state_machine.switchState(GuardiansStateMachine.States.CRAFTING_RUNES);
                Sleep.sleep(600,1200);
                return;
            }
            Walking.walk(UserAreas.GuardianAltars.getRandomTile());
            Sleep.sleep(300, 1000);
            return;
        }

        Sleep.sleepUntil(() -> !me.isMoving() || !getViableGuardians().contains(target.getName()), 10000);
        Sleep.sleep(300,900);
    }

    private GameObject getHighestPriorityGuardian(List<String> guardians) {
        if (guardians.contains("Guardian of Blood")) {
            return GameObjects.closest("Guardian of Blood");
        }
        if (guardians.contains("Guardian of Death")) {
            return GameObjects.closest("Guardian of Death");
        }
        if (guardians.contains("Guardian of Law")) {
            return GameObjects.closest("Guardian of Law");
        }
        if (guardians.contains("Guardian of Chaos")) {
            return GameObjects.closest("Guardian of Chaos");
        }
        if (guardians.contains("Guardian of Nature")) {
            return GameObjects.closest("Guardian of Nature");
        }
        if (guardians.contains("Guardian of Mind")) {
            return GameObjects.closest("Guardian of Mind");
        }
        if (guardians.contains("Guardian of Body")) {
            return GameObjects.closest("Guardian of Body");
        }
        if (guardians.contains("Guardian of Air")) {
            return GameObjects.closest("Guardian of Air");
        }
        if (guardians.contains("Guardian of Water")) {
            return GameObjects.closest("Guardian of Water");
        }
        if (guardians.contains("Guardian of Fire")) {
            return GameObjects.closest("Guardian of Fire");
        }
        if (guardians.contains("Guardian of Earth")) {
            return GameObjects.closest("Guardian of Earth");
        }
        return null;
    }

    private GameObject getGuardianTarget() {
        List<String> guardians = getViableGuardians();
        return getHighestPriorityGuardian(guardians);
    }

    private List<String> getViableGuardians() {
        List<String> currentGuardians = getCurrentGuardians();
        List<String> viableGuardians = new ArrayList<>();

        for(String guardian : currentGuardians) {
            if (getGuardianRequirements(guardian) > runecraftingLevel) {
                continue;
            }

            if (guardian.equals("Cosmic")) {
                if (!Quests.isFinished(PaidQuest.LOST_CITY)) {
                    continue;
                }
            }

            if (guardian.equals("Death")) {
                if (!Quests.isFinished(PaidQuest.MOURNINGS_END_PART_II)) {
                    continue;
                }
            }
            if (guardian.equals("Law")) {
                if (!Quests.isFinished(PaidQuest.TROLL_STRONGHOLD)) {
                    continue;
                }
            }
            if (guardian.equals("Blood")) {
                if (!Quests.isFinished(PaidQuest.SINS_OF_THE_FATHER)) {
                    continue;
                }
            }

            switch(guardian) {
                case "Air":
                    viableGuardians.add("Guardian of Air");
                    break;
                case "Water":
                    viableGuardians.add("Guardian of Water");
                    break;
                case "Earth":
                    viableGuardians.add("Guardian of Earth");
                    break;
                case "Fire":
                    viableGuardians.add("Guardian of Fire");
                    break;
                case "Law":
                    viableGuardians.add("Guardian of Law");
                    break;
                case "Nature":
                    viableGuardians.add("Guardian of Nature");
                    break;
                case "Body":
                    viableGuardians.add("Guardian of Body");
                    break;
                case "Cosmic":
                    viableGuardians.add("Guardian of Cosmic");
                    break;
                case "Chaos":
                    viableGuardians.add("Guardian of Chaos");
                    break;
                case "Death":
                    viableGuardians.add("Guardian of Death");
                    break;
                case "Blood":
                    viableGuardians.add("Guardian of Blood");
                    break;
                case "Mind":
                    viableGuardians.add("Guardian of Mind");
                    break;
            }
        }


        return viableGuardians;
    }

    private int getGuardianRequirements(String guardian) {
        switch (guardian) {
            case "Air":
                return 1;
            case "Water":
                return 5;
            case "Earth":
                return 9;
            case "Fire":
                return 14;
            case "Law":
                return 54;
            case "Nature":
                return 44;
            case "Body":
                return 20;
            case "Cosmic":
                return 27;
            case "Chaos":
                return 35;
            case "Death":
                return 65;
            case "Blood":
                return 77;
            case "Mind":
                return 2;
            default:
                // Purposely higher than possible level, this is an error state
                return 129;
        }
    }

    private List<String> getCurrentGuardians() {
        List<String> guardians = new ArrayList<String>();

        Widget parent = Widgets.getWidget(GuardiansWidgetTextureIDs.WIDGET_PARENT_ID);
        if (parent == null) {
            return guardians;
        }
        WidgetChild elementalWidgetChild = parent.getChild(GuardiansWidgetTextureIDs.ELEMENTAL_CHILD_WIDGET_ID);
        WidgetChild catalyticWidgetChild = parent.getChild(GuardiansWidgetTextureIDs.CATALYTIC_CHILD_WIDGET_ID);

        switch(elementalWidgetChild.getTextureId()) {
            case GuardiansWidgetTextureIDs.AIR_TEXTURE_ID:
                guardians.add("Air");
                break;
            case GuardiansWidgetTextureIDs.WATER_TEXTURE_ID:
                guardians.add("Water");
                break;
            case GuardiansWidgetTextureIDs.EARTH_TEXTURE_ID:
                guardians.add("Earth");
                break;
            case GuardiansWidgetTextureIDs.FIRE_TEXTURE_ID:
                guardians.add("Fire");
                break;
        }


        switch(catalyticWidgetChild.getTextureId()) {
            case GuardiansWidgetTextureIDs.LAW_TEXTURE_ID:
                guardians.add("Law");
                break;
            case GuardiansWidgetTextureIDs.NATURE_TEXTURE_ID:
                guardians.add("Nature");
                break;
            case GuardiansWidgetTextureIDs.BODY_TEXTURE_ID:
                guardians.add("Body");
                break;
            case GuardiansWidgetTextureIDs.COSMIC_TEXTURE_ID:
                guardians.add("Cosmic");
                break;
            case GuardiansWidgetTextureIDs.CHAOS_TEXTURE_ID:
                guardians.add("Chaos");
                break;
            case GuardiansWidgetTextureIDs.DEATH_TEXTURE_ID:
                guardians.add("Death");
                break;
            case GuardiansWidgetTextureIDs.BLOOD_TEXTURE_ID:
                guardians.add("Blood");
                break;
            case GuardiansWidgetTextureIDs.MIND_TEXTURE_ID:
                guardians.add("Mind");
                break;
        }

        return guardians;
    }

    @Override
    public void Enter() {
        runecraftingLevel = Skills.getRealLevel(Skill.RUNECRAFTING);
    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {
        if (message.getMessage().contains(GuardiansWidgetTextureIDs.gameEndedText) || message.getMessage().contains(GuardiansWidgetTextureIDs.gameLostText)) {
            Sleep.sleep(600,5000);
            state_machine.switchState(GuardiansStateMachine.States.PRE_GAME);
        }
    }
}
