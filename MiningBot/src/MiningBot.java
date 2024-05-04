import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(name = "Mining Bot", description = "My first mining bot!", author = "Gronker",
        version = 1.0, category = Category.MINING, image = "")
public class MiningBot extends AbstractScript {

    MiningStateMachine sm = new MiningStateMachine(new MiningIronOreInVarrockActivity());

    @Override
    public int onLoop() {
        sm.doAction();
        return 1;
    }
}