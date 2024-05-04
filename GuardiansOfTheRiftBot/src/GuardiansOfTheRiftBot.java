import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.wrappers.widgets.message.Message;

@ScriptManifest(name = "GOTR Bot",
            description = "My first minigame bot!", author = "Gronker",
            version = 0.01,
            category = Category.RUNECRAFTING,
            image = "")

public class GuardiansOfTheRiftBot extends AbstractScript implements ChatListener {

    GuardiansStateMachine sm = new GuardiansStateMachine();

    @Override
    public int onLoop() {
        sm.doAction();
        return 1;
    }

    @Override
    public void onMessage(Message message) {
        sm.chatMessageRecieved(message);
    }
}
