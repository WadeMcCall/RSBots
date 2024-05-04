import SharedBotLib.Activity;
import SharedBotLib.State;

public abstract class TheivingState extends State<TheivingActivity>{
    TheivingState(TheivingStateMachine sm) {
        super(sm);
    }
}
