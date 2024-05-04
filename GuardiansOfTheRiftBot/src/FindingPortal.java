import SharedBotLib.Activity;
import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.UserAreas;
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

        if (UserAreas.HugeGuardianRemains.contains(Players.getLocal())) {
            Logger.log(Logger.LogType.INFO, "player in huge essence mine!");
            state_machine.switchState(GuardiansStateMachine.States.MINING_HUGE_ESS);
            Sleep.sleepUntil(() -> !Players.getLocal().isMoving(), 7000);
            Sleep.sleep(400,1000);
            return;
        }
        if (UserAreas.LargeGuardianRemainsSouth.contains(Players.getLocal())) {
            GameObjects.closest("Rubble").interact();
            Sleep.sleepUntil(() -> !UserAreas.LargeGuardianRemainsSouth.contains(Players.getLocal()), 6000);
            return;
        }

        GameObject portal = GameObjects.closest(x -> x.getName().equals("Portal") && x.getID() != 43692);

        if (portal == null) {
            Logger.log(Logger.LogType.INFO, "couldn't detect player inside huge essence mine");
            state_machine.switchState(GuardiansStateMachine.States.ENTRY_STATE);
            return;
        }

        if (Map.canReach(portal) && !portal.interact()) {
            Walking.walk(portal.getTile());
            Sleep.sleepUntil(() -> Walking.getDestinationDistance() <= 3, 7000);
            Sleep.sleep(1200, 1800);
            return;
        }
        Sleep.sleepUntil(() -> UserAreas.HugeGuardianRemains.contains(Players.getLocal()),2400);
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {

    }
}
