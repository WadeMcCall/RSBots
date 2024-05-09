import SharedBotLib.UserAreas;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;

import java.util.HashMap;
import java.util.Map;

public class RFDStartActivity extends QuestActivity{
    RFDStartActivity() {
        Map<String, Integer> requiredItems = new HashMap<>();
        requiredItems.put("Eye of newt", 1);
        requiredItems.put("Greenman's ale", 1);
        requiredItems.put("Rotten tomato", 1);
        requiredItems.put("Ashes", 1);
        requiredItems.put("Fruit blast", 1);

        sections.add(createQuestSection(
                new WalkQuestAction(UserAreas.LumbyUpstairsBankArea),
                new GatherRequirementsQuestAction(requiredItems),
                new WalkQuestAction(UserAreas.LumbridgeCastleCook),
                new NPCInteractQuestAction("Cook", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(1,1,1,1),
                new UseItemQuestAction("Fruit blast", "Ashes", a -> Inventory.contains("Dirty blast")),
                new NPCInteractQuestAction("Cook", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(1),
                new WaitForQuestCompleteAction(),
                new InteractQuestAction(12348, "Open")
        ));
    }
}
