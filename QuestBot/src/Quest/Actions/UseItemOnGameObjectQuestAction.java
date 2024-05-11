package Quest.Actions;

import SharedBotLib.Utils;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;

import java.util.function.Predicate;

public class UseItemOnGameObjectQuestAction extends QuestAction {
    public String invItemName;
    public String gameObjectName;
    public int objectID;

    public UseItemOnGameObjectQuestAction(String _invItem, int _go, Predicate<UseItemOnGameObjectQuestAction> completionCheck) {
        super(a -> completionCheck.test((UseItemOnGameObjectQuestAction) a));

        invItemName = _invItem;
        objectID = _go;
    }

    public UseItemOnGameObjectQuestAction(String _invItem, String _go, Predicate<UseItemOnGameObjectQuestAction> completionCheck) {
        super(a -> completionCheck.test((UseItemOnGameObjectQuestAction) a));

        invItemName = _invItem;
        gameObjectName = _go;
    }

    public UseItemOnGameObjectQuestAction(String _invItem, String _go) {
        invItemName = _invItem;
        gameObjectName = _go;
    }

    @Override
    public ActionResult doAction() {
        if (Dialogues.inDialogue())
            Dialogues.continueDialogue();

        GameObject object;
        if (gameObjectName != null) {
            object = GameObjects.closest(gameObjectName);
        } else {
            object = GameObjects.closest(objectID);
        }
        if (!Inventory.contains(invItemName) || object == null) {
            return ActionResult.ERROR;
        }
        Inventory.get(invItemName).useOn(object);
        Sleep.sleep((int) Utils.getRandomGuassianDistNotNegative(600, 300));

        if (isComplete()) {
            return ActionResult.FINISH;
        }

        return ActionResult.CONTINUE;
    }
}
