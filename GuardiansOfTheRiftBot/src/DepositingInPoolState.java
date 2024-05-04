import SharedBotLib.Activity;
import SharedBotLib.State;
import SharedBotLib.StateMachine;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.widgets.WidgetChild;
import org.dreambot.api.wrappers.widgets.message.Message;

public class DepositingInPoolState extends State<Activity> {
    public DepositingInPoolState(StateMachine<Activity> sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (!Inventory.contains(x -> x.getName().contains("rune"))) {
            if (GuardiansStateMachine.isPortalOpen()) {
                state_machine.switchState(GuardiansStateMachine.States.FINDING_PORTAL);
                return;
            }
            state_machine.switchState(GuardiansStateMachine.States.WALKING_TO_WORKBENCH);
            return;
        }

        GameObject depositPool = GameObjects.closest("Deposit Pool");

        if (depositPool != null) {
            depositPool.interact();
            Sleep.sleepUntil(() -> !Inventory.contains(x -> x.getName().contains("rune")), 4000);
        }

    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {
        if (message.getMessage().contains(GuardiansWidgetTextureIDs.gameEndedText) || message.getMessage().contains(GuardiansWidgetTextureIDs.gameLostText)) {
            Sleep.sleep(600,5000);
            state_machine.switchState(GuardiansStateMachine.States.PRE_GAME);
        }
    }
}
