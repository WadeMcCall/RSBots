import SharedBotLib.Utils;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;

import java.util.function.Predicate;

public class UseItemOnNPCQuestAction extends QuestAction{
    public String invItemName;
    public String npcName;

    public UseItemOnNPCQuestAction(String _invItem, String _npc, Predicate<UseItemOnNPCQuestAction> completionCheck) {
        super(a -> completionCheck.test((UseItemOnNPCQuestAction) a));

        invItemName = _invItem;
        npcName = _npc;
    }

    public UseItemOnNPCQuestAction(String _invItem, String _npc) {
        invItemName = _invItem;
        npcName = _npc;
    }

    @Override
    public ActionResult doAction() {
        if (Dialogues.inDialogue())
            Dialogues.continueDialogue();

        NPC npc = NPCs.closest(npcName);
        if (!Inventory.contains(invItemName) || npc == null) {
            return ActionResult.ERROR;
        }
        Inventory.get(invItemName).useOn(npc);
        Sleep.sleepUntil(() -> isComplete(), 10000);
        Sleep.sleep((int) Utils.getRandomGuassianDistNotNegative(600, 300));

        if (isComplete()) {
            return ActionResult.FINISH;
        }

        return ActionResult.CONTINUE;
    }
}
