import SharedBotLib.Activity;
import SharedBotLib.State;

public abstract class KillingState<F extends Activity> extends State<FightingActivity>{
    KillingState(FightingStateMachine sm) {
        super(sm);
    }
}
