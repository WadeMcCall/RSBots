package Quest.Activities;

import Quest.Actions.*;
import SharedBotLib.UserAreaService;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.widget.Widgets;

import java.util.HashMap;
import java.util.Map;

public class RFDGoblinGenerals extends QuestActivity{
    RFDGoblinGenerals() {
        Map<String, Integer> requiredItems = new HashMap<>();

        requiredItems.put("Bread", 1);
        requiredItems.put("Orange", 1);
        requiredItems.put("Knife", 1);
        requiredItems.put("Purple dye", 1);
        requiredItems.put("Spice", 1);
        requiredItems.put("Fishing bait", 1);
        requiredItems.put("Bucket of water", 1);
        requiredItems.put("Charcoal", 1);

        sections.add(createQuestSection(
                new WalkQuestAction(UserAreaService.getAreaByName("LumbyUpstairsBankArea")),
                new GatherRequirementsQuestAction(requiredItems),
                new WalkQuestAction(UserAreaService.getAreaByName("LumbridgeCastleCook")),
                new InteractQuestAction(12348, "Open", k -> UserAreaService.getAreaByName("RFDDiningHall").contains(Players.getLocal())),
                new InteractQuestAction("General Wartface", "Inspect", k -> Dialogues.inDialogue()),
                createDialogueQuestAction(),
                new InteractQuestAction("Barrier", "Pass-through", k -> UserAreaService.getAreaByName("LumbyCastleGroundFloor").contains(Players.getLocal()))
        ));
        sections.add(createQuestSection(
                new WalkQuestAction(UserAreaService.getAreaByName("GoblinVillageEastLadderHouse")),
                new InteractQuestAction(12389, "Climb-down", k -> !UserAreaService.getAreaByName("GoblinVillageEastLadderHouse").contains(Players.getLocal())),
                new NPCInteractQuestAction("Goblin cook", "Talk-to", k -> Dialogues.inDialogue()),
                createDialogueQuestAction(2, 1),
                new NPCInteractQuestAction("Goblin cook", "Talk-to", k -> Dialogues.inDialogue()),
                createDialogueQuestAction(1),
                new NPCInteractQuestAction("Goblin cook", "Talk-to", k -> Dialogues.inDialogue()),
                createDialogueQuestAction(3),
                new UseItemQuestAction("Knife", "Orange", a -> Widgets.getWidget(270).isVisible()),
                createDialogueQuestAction(1),
                new UseItemQuestAction("Spice", "Fishing bait", a -> Inventory.contains("Spicy maggots")),
                new UseItemQuestAction("Bread", "Bucket of water", a -> Inventory.contains("Soggy bread")),
                new UseItemQuestAction("Purple dye", "Orange slices", a -> Inventory.contains("Dyed orange")),
                new NPCInteractQuestAction("Goblin cook", "Talk-to", k -> Dialogues.inDialogue()),
                createDialogueQuestAction(3)
        ));
        sections.add(createQuestSection(
                new InteractQuestAction("Ladder", "Climb-up", k -> UserAreaService.getAreaByName("GoblinVillageEastLadderHouse").contains(Players.getLocal())),
                new WalkQuestAction(UserAreaService.getAreaByName("LumbridgeCastleCook")),
                new InteractQuestAction(12348, "Open", k -> !UserAreaService.getAreaByName("LumbyCastleGroundFloor").contains(Players.getLocal())),
                new UseItemOnGameObjectQuestAction("Slop of compromise", "General Wartface", a -> !Inventory.contains("Slop of compromise")),
                createDialogueQuestAction()
        ));
    }
}
