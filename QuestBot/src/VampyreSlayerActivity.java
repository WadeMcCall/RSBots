import SharedBotLib.UserAreaService;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class VampyreSlayerActivity extends QuestActivity{
    public VampyreSlayerActivity() {

        Map<String, Integer> requiredItems = new HashMap<>();
        requiredItems.put("Coins", 2);
        Map<String, Integer> conditionalItems = new HashMap<>();
        conditionalItems.put("Coins", 2);
        conditionalItems.put("Hammer", 1);
        conditionalItems.put("Garlic", 1);

        sections.add(createQuestSection(
                new WalkQuestAction(UserAreaService.getAreaByName("DraynorBank")),
                new GatherRequirementsQuestAction(requiredItems),
                new WalkQuestAction(UserAreaService.getAreaByName("DraynorMorganNPCHouse")),
                new NPCInteractQuestAction("Morgan", "Talk-to", k -> Dialogues.inDialogue()),
                createDialogueQuestAction(1),
                new ConditionalQuestAction(
                        action -> !Inventory.contains("Garlic"),
                        new WalkQuestAction(UserAreaService.getAreaByName("DraynorUpstairsMorgan")),
                        new InteractQuestAction("Cupboard", "Open", a -> GameObjects.closest("Cupboard").hasAction("Search")),
                        new InteractQuestAction("Cupboard", "Search", a -> Inventory.contains("Garlic"))
                )
        ));
        sections.add(createQuestSection(
                new WalkQuestAction(UserAreaService.getAreaByName("BlueMoonInn")),
                new NPCInteractQuestAction("Dr Harlow", "Talk-to", k -> Dialogues.inDialogue()),
                createDialogueQuestAction(2),
                new NPCInteractQuestAction("Bartender", "Talk-to", k -> Dialogues.inDialogue()),
                createDialogueQuestAction(1),
                new NPCInteractQuestAction("Dr Harlow", "Talk-to", k -> Dialogues.inDialogue()),
                createDialogueQuestAction()
        ));
        requiredItems.clear();
        conditionalItems.clear();
        requiredItems.put("Stake", 1);
        requiredItems.put("Hammer", 1);
        requiredItems.put("Garlic", 1);
        sections.add(createQuestSection(
                new WalkQuestAction(UserAreaService.getAreaByName("DraynorBank")),
                new GatherRequirementsQuestAction(requiredItems, true),
                new WalkQuestAction(UserAreaService.getAreaByName("CountDraynorCoffin")),
                new InteractQuestAction("Coffin", "Open", a -> NPCs.closest("Count Draynor") != null),
                new NPCInteractQuestAction("Count Draynor", "Attack", k -> Players.getLocal().isInCombat()),
                new SimpleCombatQuestAction(3, "Count Draynor"),
                new WaitForQuestCompleteAction()
        ));
    }
}
