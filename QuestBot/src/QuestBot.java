import SharedBotLib.Activity;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(name = "Questing Bot", description = "My first questing bot!", author = "Gronker",
        version = 1.0, category = Category.QUEST, image = "")
public class QuestBot extends AbstractScript {
    QuestingStateMachine sm = new QuestingStateMachine(new VampyreSlayerActivity());

    @Override
    public int onLoop() {
        sm.doAction();
        return 1;
    }
}
