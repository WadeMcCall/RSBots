import SharedBotLib.StateMachine;

public class TheivingStateMachine extends StateMachine<TheivingActivity> {
    TheivingStateMachine(TheivingActivity _activity) {
        addState(States.WALKING_TO_BANK_STATE, new WalkingToBankState(this));
        addState(States.USE_BANK_STATE, new UseBankState(this));
        addState(States.BANKING_STATE, new BankingState(this));
        addState(States.WALKING_TO_SPOT_STATE, new WalkingToSpotState(this));
        addState(States.THEIVING_TARGET_STATE, new TheivingTargetState(this));
        addState(States.STUNNED_STATE, new StunnedState(this));

        activity = _activity;
        switchState(States.WALKING_TO_SPOT_STATE);
    }
    public enum States {
        WALKING_TO_BANK_STATE,
        USE_BANK_STATE,
        BANKING_STATE,
        WALKING_TO_SPOT_STATE,
        THEIVING_TARGET_STATE,
        STUNNED_STATE
    }
}
