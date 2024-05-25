import SharedBotLib.Activity;
import SharedBotLib.StateMachine;

public class FarmingStateMachine extends StateMachine {

    FarmingStateMachine() {
        addState(States.PLANTING, new PlantingState(this));
        addState(States.WATERING, new WateringState(this));
        addState(States.TURNING_IN, new TurningInState(this));
        addState(States.REFILLING, new RefillingState(this));
        addState(States.WALKING_TO_START_POSITION, new WalkingToStartPositionState(this));
    }

    public enum States {
        PLANTING,
        WATERING,
        TURNING_IN,
        REFILLING,
        WALKING_TO_START_POSITION
    }
}
