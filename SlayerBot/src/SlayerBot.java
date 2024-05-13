import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(name = "Slayer Bot", description = "My first Slayer bot!", author = "Gronker",
        version = 1.0, category = Category.SLAYER, image = "")
public class SlayerBot extends AbstractScript {

    SlayerStateMachine sm = new SlayerStateMachine();

    @Override
    public int onLoop() {
        sm.doAction();
        return 1;
    }
}