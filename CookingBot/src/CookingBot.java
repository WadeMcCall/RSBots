import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(name = "Cooking Bot", description = "My first cooking bot!", author = "Gronker",
        version = 1.0, category = Category.MINING, image = "")
public class CookingBot extends AbstractScript{

    CookingStateMachine sm = new CookingStateMachine(new LumbridgeCookingActivity());

    @Override
    public int onLoop() {
        sm.doAction();
        return 1;
    }
}
