import org.dreambot.api.input.Keyboard;
import org.dreambot.api.methods.dialogues.Dialogues;

import java.util.Queue;

public class DialogueQuestAction extends QuestAction {
    public Queue<Integer> dialogueOptions;

    public DialogueQuestAction(Queue<Integer> _options) {
        dialogueOptions = _options;
    }

    @Override
    public ActionResult doAction() {
        if (Dialogues.canContinue()) {
            Dialogues.continueDialogue();
            return ActionResult.CONTINUE;
        }
        if (!Dialogues.inDialogue()) {
            return ActionResult.FINISH;
        }
        if (Dialogues.areOptionsAvailable()) {
            if (dialogueOptions.isEmpty())
                return ActionResult.FINISH;
            int option = dialogueOptions.remove();
            Keyboard.type(option, false);
            return ActionResult.CONTINUE;
        }
        return ActionResult.CONTINUE;
    }
}
