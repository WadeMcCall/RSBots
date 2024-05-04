import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.wrappers.widgets.message.Message;

@ScriptManifest(name = "WoodcutFMBot", description = "Trains Firemaking!", author = "Gronker",
        version = 1.0, category = Category.FIREMAKING, image = "")
public class WoodcuttingFMBot extends AbstractScript implements ChatListener {
    WoodcutFMStateMachine sm = new WoodcutFMStateMachine();

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
