import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.wrappers.widgets.message.Message;

@ScriptManifest(name = "GOTR Bot",
        description = "My first minigame bot!", author = "Gronker",
        version = 0.01,
        category = Category.RUNECRAFTING,
        image = "")

public class TemporossBot extends AbstractScript implements ChatListener {


    TemporossStateMachine sm = new TemporossStateMachine();

    @Override
    public int onLoop() {
        return 0;
    }

    @Override
    public void onMessage(Message message) {
        sm.chatMessageRecieved(message);
    }
}
