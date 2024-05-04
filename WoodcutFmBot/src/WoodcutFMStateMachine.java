import SharedBotLib.Activity;
import SharedBotLib.StateMachine;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(name = "WoodcuttingFMBot", description = "This bot will woodcut and firemake with a goal of 50 firemaking for wintertodt", author = "Gronker",
        version = 1.0, category = Category.THIEVING, image = "")
public class WoodcutFMStateMachine extends StateMachine<Activity> {

    WoodcutFMStateMachine() {
        addState(States.FINDING_TREE, new FindingTreeState(this));
        addState(States.FIREMAKING, new FiremakingState(this));
        addState(States.CUTTING_TREE, new CuttingTreeState(this));

        switchState(States.FINDING_TREE);
    }

    public enum States {
        FINDING_TREE,
        CUTTING_TREE,
        FIREMAKING,
    }
}
