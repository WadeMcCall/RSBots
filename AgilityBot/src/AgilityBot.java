import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(name = "Agility Bot", description = "My first agility bot!", author = "Gronker",
        version = 1.0, category = Category.AGILITY, image = "")

public class AgilityBot extends AbstractScript{

    AgilityStateMachine sm = new AgilityStateMachine(new FaladorRooftopActivity());

    @Override
    public int onLoop() {
        sm.doAction();
        return 1;
    }
}
