import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(name = "Theiving Bot", description = "My first Theiving bot!", author = "Gronker",
        version = 1.0, category = Category.THIEVING, image = "")
public class TheivingBot extends AbstractScript {

    TheivingStateMachine sm = new TheivingStateMachine(new TheivingGuardsInVarrockActivity());

    @Override
    public int onLoop() {
        sm.doAction();
        return 1;
    }
}