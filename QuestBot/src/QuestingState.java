import Actions.QuestAction;
import Activities.QuestActivity;
import SharedBotLib.State;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.wrappers.widgets.message.Message;
import org.dreambot.api.utilities.Logger.LogType;

public class QuestingState extends State<QuestActivity> {
    public QuestingState(QuestingStateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        QuestAction.ActionResult result = state_machine.activity.executeQuestAction();
        if (result == QuestAction.ActionResult.ERROR) {
            Logger.log(LogType.ERROR, "Encountered an error, stopping quest.");
        }
        if (result == QuestAction.ActionResult.FINISH) {
            ScriptManager.getScriptManager().stop();
        }
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {

    }
}
