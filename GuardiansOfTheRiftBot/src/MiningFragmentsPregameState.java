import SharedBotLib.Activity;
import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.Utils;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.widgets.WidgetChild;
import org.dreambot.api.wrappers.widgets.message.Message;

public class MiningFragmentsPregameState extends State<Activity> {

    private boolean guardiansAvailable = false;
    private boolean portalSpawned = false;
    private int initialFragments;

    public MiningFragmentsPregameState(StateMachine<Activity> sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        Item fragments = Inventory.get("Guardian fragments");
        if(portalSpawned) {
            state_machine.switchState(GuardiansStateMachine.States.FINDING_PORTAL);
        }

        if (fragments != null) {
            if (guardiansAvailable && fragments.getAmount() >= initialFragments) {
                state_machine.switchState(GuardiansStateMachine.States.WALKING_TO_WORKBENCH);
                return;
            }
        }
        Player me = Players.getLocal();

        if (me.isAnimating() || me.isMoving()) {
            return;
        }

        String[] guardianNames = {"Guardian remains", "Large guardian remains"};
        GameObjects.closest(guardianNames).interact();
        Sleep.sleepUntil(() ->Players.getLocal().isAnimating(), 1200);
    }

    @Override
    public void Enter() {
        guardiansAvailable = false;
        portalSpawned = false;
        initialFragments = Utils.getRandomInt(200,250);
    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {
        if (message.getMessage().contains("portal to the huge")) {
            portalSpawned = true;
        }
        if (message.getMessage().contains("no need to do that")) {
            Logger.log(Logger.LogType.INFO, "gameEnded fail-safe");
            Sleep.sleep((int)Utils.getRandomGuassianDistNotNegative(3000, 800));
            state_machine.switchState(GuardiansStateMachine.States.PRE_GAME);
        }
        if (message.getMessage().contains(GuardiansWidgetTextureIDs.guardiansAvailableText)) {
            guardiansAvailable = true;
        }
    }
}
