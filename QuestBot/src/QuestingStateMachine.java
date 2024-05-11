import Activities.QuestActivity;
import SharedBotLib.StateMachine;

public class QuestingStateMachine extends StateMachine<QuestActivity> {
    QuestingStateMachine(QuestActivity _activity) {
        addState(States.QUESTING, new QuestingState(this));

        activity = _activity;
        switchState(States.QUESTING);
    }

    public enum States {
        QUESTING,
        QUEST_BOSS
    }
}
