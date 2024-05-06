import SharedBotLib.Activity;
import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.Utils;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.widgets.WidgetChild;
import org.dreambot.api.wrappers.widgets.message.Message;

public class MiningDuringGameState extends State<Activity> {
    private int goalEss;

    public MiningDuringGameState(StateMachine<Activity> sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Inventory.contains("Guardian fragments")) {
            if (Inventory.get("Guardian fragments").getAmount() >= goalEss) {
                state_machine.switchState(GuardiansStateMachine.States.WALKING_TO_WORKBENCH);
                return;
            }
        }

        if (GuardiansStateMachine.isPortalOpen()) {
            state_machine.switchState(GuardiansStateMachine.States.FINDING_PORTAL);
            return;
        }

        if (Players.getLocal().isAnimating() || Players.getLocal().isMoving()) {
            return;
        }

        String[] guardianNames = {"Guardian remains", "Large guardian remains"};
        GameObjects.closest(guardianNames).interact();
    }

    @Override
    public void Enter() {
        goalEss = Utils.getRandomInt(40, 70);
    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {
        if (message.getMessage().contains("no need to do that")) {
            Logger.log(Logger.LogType.INFO, "gameEnded fail-safe");
            Sleep.sleep((int)Utils.getRandomGuassianDistNotNegative(3000, 800));
            state_machine.switchState(GuardiansStateMachine.States.PRE_GAME);
        }
        if (message.getMessage().contains(GuardiansWidgetTextureIDs.gameEndedText) || message.getMessage().contains(GuardiansWidgetTextureIDs.gameLostText)) {
            Sleep.sleep((int)Utils.getRandomGuassianDistNotNegative(3000, 800));
            state_machine.switchState(GuardiansStateMachine.States.PRE_GAME);
        }
    }
}
