import SharedBotLib.StateMachine;

public class FishingStateMachine extends StateMachine<FishingActivity> {

    public enum States {
        WALKING_TO_BANK_STATE,
        USE_BANK_STATE,
        WALKING_TO_SPOT_STATE,
        BANKING_STATE,
        FISHING_AT_SPOT_STATE,
        FINDING_SPOT_STATE
    }

    public FishingStateMachine(FishingActivity _activity) {
        addState(States.WALKING_TO_BANK_STATE, new WalkingToBankState(this));
        addState(States.USE_BANK_STATE, new UseBankState(this));
        addState(States.WALKING_TO_SPOT_STATE, new WalkingToSpotState(this));
        addState(States.BANKING_STATE, new BankingState(this));
        addState(States.FISHING_AT_SPOT_STATE, new FishingAtSpotState(this));
        addState(States.FINDING_SPOT_STATE, new FindingSpotState(this));

        activity = _activity;
        switchState(States.FINDING_SPOT_STATE); // default state
    }
}
