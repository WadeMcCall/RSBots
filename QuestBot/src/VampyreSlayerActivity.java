import SharedBotLib.UserAreas;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class VampyreSlayerActivity extends QuestActivity{
    public VampyreSlayerActivity() {

        Map<String, Integer> requiredItems = new HashMap<>();
        requiredItems.put("Hammer", 1);
        requiredItems.put("Coins", 2);

        sections.add(createQuestSection(
                new WalkQuestAction(UserAreas.DraynorBank),
                new GatherRequirementsQuestAction(requiredItems),
                new WalkQuestAction(UserAreas.DraynorMorganNPCHouse),
                new NPCInteractQuestAction("Morgan", "Talk-to", k -> Dialogues.inDialogue()),
                createDialogueQuestAction(1),
                new WalkQuestAction(UserAreas.DraynorUpstairsMorgan),
                new InteractQuestAction("Cupboard", "Open", a -> GameObjects.closest("Cupboard").hasAction("Search")),
                new InteractQuestAction("Cupboard", "Search", a -> Inventory.contains("Garlic"))
        ));
        sections.add(createQuestSection(
                new WalkQuestAction(UserAreas.BlueMoonInn),
                new NPCInteractQuestAction("Dr Harlow", "Talk-to", k -> Dialogues.inDialogue()),
                createDialogueQuestAction(2),
                new NPCInteractQuestAction("Bartender", "Talk-to", k -> Dialogues.inDialogue()),
                createDialogueQuestAction(1),
                new NPCInteractQuestAction("Dr Harlow", "Talk-to", k -> Dialogues.inDialogue()),
                createDialogueQuestAction()
        ));
        requiredItems.clear();
        sections.add(createQuestSection(
                new WalkQuestAction(UserAreas.DraynorBank),
                new GatherRequirementsQuestAction(requiredItems, true),
                new WalkQuestAction(UserAreas.CountDraynorCoffin),
                new InteractQuestAction("Coffin", "Open", a -> NPCs.closest("Count Draynor") != null),
                new NPCInteractQuestAction("Count Draynor", "Attack", k -> NPCs.closest("Count Draynor") == null),
                new WaitForQuestCompleteAction()
        ));
    }
}
