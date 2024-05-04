import SharedBotLib.State;
import SharedBotLib.StateMachine;

public class FightingStateMachine extends StateMachine<FightingActivity> {
    FightingStateMachine(FightingActivity _activity) {
        addState(States.WALKING_TO_BANK_STATE, new WalkingToBankState(this));
        addState(States.USE_BANK_STATE, new UseBankState(this));
        addState(States.BANKING_STATE, new BankingState(this));
        addState(States.WALKING_TO_SPOT_STATE, new WalkingToSpotState(this));
        addState(States.FIGHTING_AT_SPOT_STATE, new FightingAtSpotState(this));
        addState(States.FINDING_TARGET_STATE, new FindingTargetState(this));
        addState(States.LOOTING_STATE, new LootingState(this));

        activity = _activity;
        switchState(States.WALKING_TO_SPOT_STATE);
    }

    public enum States {
        WALKING_TO_BANK_STATE,
        USE_BANK_STATE,
        BANKING_STATE,
        WALKING_TO_SPOT_STATE,
        FIGHTING_AT_SPOT_STATE,
        FINDING_TARGET_STATE,
        LOOTING_STATE
    }
}
