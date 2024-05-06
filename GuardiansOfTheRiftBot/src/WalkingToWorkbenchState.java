import SharedBotLib.*;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.api.wrappers.widgets.message.Message;

public class WalkingToWorkbenchState extends State<Activity> {
    public WalkingToWorkbenchState(StateMachine<Activity> sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        Player me = Players.getLocal();
        if (UserAreas.LargeGuardianRemainsSouth.contains(me)) {
            GameObjects.closest("Rubble").interact();
            Sleep.sleepUntil(() -> !UserAreas.LargeGuardianRemainsSouth.contains(me), 5000);
            return;
        }
        if (GuardiansStateMachine.isPortalOpen()) {
            state_machine.switchState(GuardiansStateMachine.States.FINDING_PORTAL);
            return;
        }

        if (Inventory.isFull()) {
            state_machine.switchState(GuardiansStateMachine.States.FINDING_GUARDIAN);
            return;
        }

        if (!Inventory.contains("Guardian fragments")) {
            state_machine.switchState(GuardiansStateMachine.States.MINING_DURING_GAME);
            return;
        }

        GameObject workbench = GameObjects.closest("Workbench");

        if (UserAreas.GuardiansWorkbench.contains(me) || workbench.distance() < 10) {
            workbench.interact();
            Sleep.sleepUntil(() -> me.isAnimating(), 2000);
            state_machine.switchState(GuardiansStateMachine.States.CRAFTING_FRAGMENTS);
            return;
        }

        Walking.walk(UserAreas.GuardiansWorkbench);
        Sleep.sleepUntil(() -> Walking.getDestinationDistance() <= 3, 5000);
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {
        if (message.getMessage().contains(GuardiansWidgetTextureIDs.gameEndedText)) {
            Sleep.sleep((int) Utils.getRandomGuassianDistNotNegative(3000, 800));
            state_machine.switchState(GuardiansStateMachine.States.PRE_GAME);
        }
    }
}
