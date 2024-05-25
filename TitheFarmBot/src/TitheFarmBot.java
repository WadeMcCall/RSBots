import SharedBotLib.StateMachine;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(name = "Tithe farm Bot", description = "My first farming bot!", author = "Gronker",
        version = 1.0, category = Category.FARMING, image = "")
public class TitheFarmBot extends AbstractScript {

    FarmingStateMachine sm = new FarmingStateMachine();

    @Override
    public int onLoop() {
        sm.doAction();
        return 1;
    }
}