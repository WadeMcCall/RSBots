import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(name = "Killing Bot", description = "My first combat bot!", author = "Gronker",
        version = 1.0, category = Category.COMBAT, image = "")
public class KillingBot extends AbstractScript {

    FightingStateMachine sm = new FightingStateMachine(new MossGiantsInVarrockSewers());

    @Override
    public int onLoop() {
        sm.doAction();
        return 1;
    }
}