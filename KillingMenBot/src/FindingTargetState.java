import SharedBotLib.Activity;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.widgets.message.Message;

public class FindingTargetState extends KillingState<Activity> {
    FindingTargetState(FightingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (!Inventory.contains(state_machine.activity.currentFoodItem.getName())) {
            state_machine.switchState(FightingStateMachine.States.WALKING_TO_BANK_STATE);
            return;
        } else if (Players.getLocal().isInCombat()){
            state_machine.switchState(FightingStateMachine.States.FIGHTING_AT_SPOT_STATE);
            return;
        }
        if (!Players.getLocal().isAnimating() && !Players.getLocal().isMoving()) {
            NPC target = NPCs.closest(n -> n.getName().contains(state_machine.activity.enemyName)
                    && n.hasAction("Attack")
                    && !n.isHealthBarVisible()
                    && (n.getInteractingCharacter() == null || n.getInteractingCharacter() == Players.getLocal())
                    && Map.canReach(n));
            if (target != null && target.interact("Attack")) {
                Sleep.sleepUntil(() -> Players.getLocal().isInCombat(), 3000);
            }
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

    }
}
