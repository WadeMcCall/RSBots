import SharedBotLib.StateMachine;

public class CookingStateMachine extends StateMachine<CookingActivity> {
    public CookingStateMachine(CookingActivity _activity) {
        addState(States.WALKING_TO_BANK, new WalkingToBankState(this));
        addState(States.USE_BANK, new UseBankState(this));
        addState(States.WALKING_TO_SPOT, new WalkingToSpotState(this));
        addState(States.BANKING, new BankingState(this));
        addState(States.FINDING_SPOT, new FindingSpotState(this));
        addState(States.COOKING, new CookingAtSpotState(this));

        activity = _activity;
        switchState(States.WALKING_TO_BANK); // default state
    }

    public enum States {
        WALKING_TO_BANK,
        USE_BANK,
        WALKING_TO_SPOT,
        BANKING,
        FINDING_SPOT,
        COOKING
    }
}
