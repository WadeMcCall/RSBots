import SharedBotLib.Activity;
import SharedBotLib.StateMachine;

public class ConstructionStateMachine extends StateMachine<ConstructionActivity> {
    ConstructionStateMachine(ConstructionActivity _activity) {
        addState(States.BUILDING, new BuildingState(this));
        addState(States.GOING_TO_BANK, new GoingToBankState(this));
        addState(States.BANKING, new BankingState(this));
        addState(States.GOING_TO_HOUSE, new GoingToHouseState(this));

        activity = _activity;
        switchState(States.GOING_TO_BANK);
    }
    public enum States {
        GOING_TO_BANK,
        BANKING,
        BUILDING,
        GOING_TO_HOUSE
    }
}
