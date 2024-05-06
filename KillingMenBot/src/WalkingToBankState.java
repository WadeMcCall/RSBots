import SharedBotLib.Activity;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.widgets.message.Message;

public class WalkingToBankState extends KillingState<Activity> {


    WalkingToBankState(FightingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (state_machine.activity.bankArea.contains(Players.getLocal().getTile())){
            state_machine.switchState(FightingStateMachine.States.USE_BANK_STATE);
            return;
        }
        if (Walking.getDestinationDistance() <= 3) {
            Walking.walk(state_machine.activity.bankArea.getRandomTile());
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
