import SharedBotLib.*;

public abstract class FishingState extends State<FishingActivity> {
    FishingState(FishingStateMachine sm) {
        super(sm);
    }
}
