import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(name = "Fishing Bot", description = "My first fishing bot!", author = "Gronker",
        version = 1.0, category = Category.MINING, image = "")
public class FishingBot extends AbstractScript {

    FishingStateMachine sm = new FishingStateMachine(new DraynorBaitFishing());

    @Override
    public int onLoop() {
        sm.doAction();
        return 1;
    }
}