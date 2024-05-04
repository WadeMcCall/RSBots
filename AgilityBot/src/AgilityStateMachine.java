import SharedBotLib.StateMachine;

public class AgilityStateMachine extends StateMachine<AgilityActivity> {
    AgilityStateMachine(AgilityActivity _activity) {
        addState(States.DOING_COURSE, new DoingCourseState(this));
        addState(States.FINDING_OBSTACLE, new FindingNextObstacleState(this));
        addState(States.FELL, new FellState(this));


        switchState(States.FINDING_OBSTACLE);
        activity = _activity;
    }

    public enum States {
        DOING_COURSE,
        FINDING_OBSTACLE,
        FELL
    }
}
