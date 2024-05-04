import SharedBotLib.*;

public abstract class MiningState extends State<MiningActivity> {
    MiningState(MiningStateMachine sm) {
        super(sm);
    }
}
