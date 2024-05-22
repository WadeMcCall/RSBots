import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(name = "Construction Bot", description = "My first con bot!", author = "Gronker",
        version = 1.0, category = Category.CONSTRUCTION, image = "")

public class ConstructionBot extends AbstractScript{

    ConstructionStateMachine sm = new ConstructionStateMachine(new OakLardersActivity());

    @Override
    public int onLoop() {
        sm.doAction();
        return 1;
    }
}
