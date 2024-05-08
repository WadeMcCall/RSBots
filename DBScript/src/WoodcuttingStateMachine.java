import SharedBotLib.StateMachine;

public class WoodcuttingStateMachine extends StateMachine<WoodcuttingActivity> {
    WoodcuttingStateMachine(WoodcuttingActivity _activity) {
        addState(States.WALKING_TO_BANK_STATE, new WalkingToBankState(this));
        addState(States.USE_BANK_STATE, new UseBankState(this));
        addState(States.BANKING_STATE, new BankingState(this));
        addState(States.WALKING_TO_TREES_STATE, new WalkingToTreesState(this));
        addState(States.CHOPPING_STATE, new ChoppingState(this));
        addState(States.FINDING_TREE_STATE, new FindingTreeState(this));

        activity = _activity;
        switchState(States.WALKING_TO_TREES_STATE);
    }

    public enum States {
        BANKING_STATE,
        FINDING_TREE_STATE,
        USE_BANK_STATE,
        WALKING_TO_BANK_STATE,
        WALKING_TO_TREES_STATE,
        CHOPPING_STATE
    }
}
