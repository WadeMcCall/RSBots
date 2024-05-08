import org.dreambot.api.input.Keyboard;
import org.dreambot.api.methods.dialogues.Dialogues;

import java.util.Stack;

public class DialogueQuestAction extends QuestAction {
    public Stack<Integer> dialogueOptions;

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
            int option = dialogueOptions.pop();
            Keyboard.type(option, false);
            return ActionResult.CONTINUE;
        }
        return ActionResult.ERROR;
    }
}
