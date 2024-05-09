import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.widget.Widgets;

public class WaitForQuestCompleteAction extends QuestAction{
    private static final int QUEST_COMPLETE_WIDGET_ID = 153;

    @Override
    public ActionResult doAction() {
        if (Widgets.getWidget(QUEST_COMPLETE_WIDGET_ID).isVisible())
            return ActionResult.FINISH;
        if (Dialogues.canContinue())
            Dialogues.continueDialogue();
        return ActionResult.CONTINUE;
    }
}
