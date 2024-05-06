import SharedBotLib.*;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.widgets.message.Message;

import java.util.Objects;

public class FindingPortal extends State<Activity> {
    public FindingPortal(StateMachine<Activity> sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Walking.getDestinationDistance() > 7)
            return;
        if (!GuardiansStateMachine.isGameStarted()) {
            state_machine.switchState(GuardiansStateMachine.States.ENTRY_STATE);
            return;
        }
        if (!GuardiansStateMachine.isPortalOpen()) {
            state_machine.switchState(GuardiansStateMachine.States.WALKING_TO_WORKBENCH);
            return;
        }

        if (UserAreas.HugeGuardianRemains.contains(Players.getLocal())) {
            Logger.log(Logger.LogType.INFO, "player in huge essence mine!");
            state_machine.switchState(GuardiansStateMachine.States.MINING_HUGE_ESS);
            Sleep.sleepUntil(() -> !Players.getLocal().isMoving(), 7000);
            Sleep.sleep((int) Utils.getRandomGuassianDistNotNegative(700, 200));
            return;
        }

        if (UserAreas.LargeGuardianRemainsSouth.contains(Players.getLocal())) {
            GameObjects.closest("Rubble").interact();
            Sleep.sleepUntil(() -> !UserAreas.LargeGuardianRemainsSouth.contains(Players.getLocal()), 6000);
            return;
        }

        GameObject portal = GameObjects.closest(x -> x.getName().equals("Portal") && x.getID() != 43692);

        if (Map.canReach(portal) && !portal.interact()) {
            Walking.walk(portal.getTile());
            Sleep.sleepUntil(() -> UserAreas.HugeGuardianRemains.contains(Players.getLocal()), 7000);
            Sleep.sleep(1200, 1800);
            return;
        }
        Sleep.sleepUntil(() -> UserAreas.HugeGuardianRemains.contains(Players.getLocal()),5000);
        Sleep.sleep((int)Utils.getRandomGuassianDistNotNegative(900, 150));
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
            Sleep.sleep((int) Utils.getRandomGuassianDistNotNegative(3000, 800));
            state_machine.switchState(GuardiansStateMachine.States.PRE_GAME);
        }
    }
}
