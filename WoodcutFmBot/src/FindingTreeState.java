import SharedBotLib.Activity;
import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.Utils;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.widgets.message.Message;

public class FindingTreeState extends State<Activity> {
    public FindingTreeState(StateMachine<Activity> sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Dialogues.canContinue()) {
            Dialogues.continueDialogue();
            Sleep.sleep(50,200);
            return;
        }
        if (Inventory.isFull()) {
            state_machine.switchState(WoodcutFMStateMachine.States.FIREMAKING);
            return;
        }
        int fmLevel = Skills.getRealLevel(Skill.FIREMAKING);
        int wcLevel = Skills.getRealLevel(Skill.WOODCUTTING);

        String treeName;
        if (fmLevel > 45 && wcLevel > 45) {
            treeName = "Maple tree";
        } else if (fmLevel > 30 && wcLevel > 30) {
            treeName = "Willow tree";
        } else if (fmLevel > 15 && wcLevel > 15) {
            treeName = "Oak tree";
        } else {
            treeName = "Tree";
        }

        GameObject tree = GameObjects.closest(treeName);
        if (!tree.canReach()) {
            Walking.walk(Utils.getRandomEmptyNearbyTile());
        }

        if (tree != null) {
            tree.interact();
            Sleep.sleepUntil(() -> Players.getLocal().isAnimating(), 7000);
            Sleep.sleep(200, 1200);
            state_machine.switchState(WoodcutFMStateMachine.States.CUTTING_TREE);
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
