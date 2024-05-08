
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(name = "Woodcutting Bot", description = "My first woodcutting bot!", author = "Gronker",
        version = 1.0, category = Category.WOODCUTTING, image = "")
public class WoodcuttingBot extends AbstractScript {

    WoodcuttingStateMachine sm = new WoodcuttingStateMachine(new NormalTreeInWestVarrockActivity());

    @Override
    public int onLoop() {
        sm.doAction();
        return 1;
    }
}