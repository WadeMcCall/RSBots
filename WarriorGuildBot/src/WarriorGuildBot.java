import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(name = "Warrior Guild Bot", description = "Gets defenders for you.", author = "Gronker",
        version = 1.0, category = Category.SLAYER, image = "")
public class WarriorGuildBot extends AbstractScript {

    WarriorStateMachine sm = new WarriorStateMachine();

    @Override
    public int onLoop() {
        sm.doAction();
        return 1;
    }
}