import SharedBotLib.StateMachine;

public class MiningStateMachine extends StateMachine<MiningActivity> {

    public enum States {
        WALKING_TO_BANK_STATE,
        USE_BANK_STATE,
        WALKING_TO_SPOT_STATE,
        BANKING_STATE,
        ENTRY_STATE,
        FINDING_SPOT_STATE,
        MINING_AT_SPOT_STATE
    }

    MiningStateMachine(MiningActivity _activity) {
        addState(States.WALKING_TO_BANK_STATE, new WalkingToBankState(this));
        addState(States.USE_BANK_STATE, new UseBankState(this));
        addState(States.WALKING_TO_SPOT_STATE, new WalkingToSpotState(this));
        addState(States.BANKING_STATE, new BankingState(this));
        addState(States.MINING_AT_SPOT_STATE, new MiningAtSpotState(this));
        addState(States.FINDING_SPOT_STATE, new FindingSpotState(this));
        addState(States.ENTRY_STATE, new EntryState(this));

        activity =_activity;
        switchState(States.WALKING_TO_SPOT_STATE); // default state
    }
}
