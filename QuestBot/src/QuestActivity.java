import SharedBotLib.Activity;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.utilities.Logger;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QuestActivity extends Activity {
    protected final Queue<QuestSection> sections = new LinkedList<>();

    private QuestSection currentSection;
    private QuestAction currentAction;

    protected DialogueQuestAction createDialogueQuestAction(Integer... options) {
        Queue<Integer> optionsList = new LinkedList<>();
        Collections.addAll(optionsList, options);
        return new DialogueQuestAction(optionsList);
    }

    protected QuestSection createQuestSection(QuestAction... actions) {
        Queue<QuestAction> queue = new LinkedList<>();
        Collections.addAll(queue, actions);
        return new QuestSection(queue);
    }

    public QuestAction.ActionResult executeQuestAction() {
        if (Dialogues.isProcessing())
            return QuestAction.ActionResult.CONTINUE;

        if (currentAction == null) {
            Logger.log("currentAction is null, finding next action");
            if (!moveToNextSection()) {
                return QuestAction.ActionResult.FINISH;
            }
        }

        QuestAction.ActionResult result = currentAction.doAction();
        if (result == QuestAction.ActionResult.FINISH) {
            currentAction = currentSection.getNextQuestAction();
            if (currentAction == null && !moveToNextSection()) {
                return QuestAction.ActionResult.FINISH;
            }
            result = QuestAction.ActionResult.CONTINUE;
        }

        return result;
    }

    private boolean moveToNextSection() {
        currentSection = sections.poll();
        Logger.log("in moveToNextSection");
        if (currentSection != null) {
            currentAction = currentSection.getNextQuestAction();
            return true;
        }
        return false;
    }
}
