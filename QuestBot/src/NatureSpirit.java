import SharedBotLib.UserAreaService;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.widget.Widgets;

import java.util.HashMap;
import java.util.Map;

public class NatureSpirit extends QuestActivity {
    NatureSpirit() {
        Map<String, Integer> requiredItems = new HashMap<>();
        requiredItems.put("Ghostspeak amulet", 1);

        sections.add(createQuestSection(
                new WalkQuestAction(UserAreaService.getAreaByName("EastVarrockBankArea")),
                new GatherRequirementsQuestAction(requiredItems),
                new WalkQuestAction(UserAreaService.getAreaByName("DrezelPaterdomus")),
                new NPCInteractQuestAction("Drezel", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(1, 1),
                new InteractQuestAction("Holy barrier", "Pass-through", a -> Players.getLocal().getTile().equals(new Tile(3423, 3485))),
                new WalkQuestAction(UserAreaService.getAreaByName("MortMyreSwampCanifisEntrance")),
                new InteractQuestAction("Gate", "Open", a -> (!UserAreaService.getAreaByName("MortMyreSwampCanifisEntrance").contains(Players.getLocal()))),
                new InventoryInteractQuestAction("Ghostspeak amulet", "Wear", a -> !Inventory.contains("Ghostspeak amulet")),
                new WalkQuestAction(UserAreaService.getAreaByName("NatureGrottoAgility")),
                new InteractQuestAction("Bridge", "Jump", a -> (UserAreaService.getAreaByName("NatureGrotto").contains(Players.getLocal())) && !Players.getLocal().isAnimating()),
                new WalkQuestAction(UserAreaService.getAreaByName("NatureGrotto")),
                new InteractQuestAction("Grotto", "Enter", a -> Dialogues.inDialogue()),
                new NPCInteractQuestAction("Filliman Tarlock", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(2, 4),
                new GroundItemInteractQuestAction("Washing bowl", "Take", a -> Inventory.contains("Washing bowl")),
                new GroundItemInteractQuestAction("Mirror", "Take", a -> Inventory.contains("Mirror")),
                new UseItemOnNPCQuestAction("Mirror", "Filliman Tarlock", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(),
                new InteractQuestAction("Grotto tree", "Search", a -> Inventory.contains("Journal")),
                new UseItemOnNPCQuestAction("Journal", "Filliman Tarlock", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(4),
                new InteractQuestAction("Bridge", "Jump", a -> !UserAreaService.getAreaByName("NatureGrotto").contains(Players.getLocal()))
        ));

        requiredItems.put("Silver sickle", 1);
        Tile ThreeLogTile = new Tile(3414, 3360);
        sections.add(createQuestSection(
                new WalkQuestAction(UserAreaService.getAreaByName("CanifisBank")),
                new GatherRequirementsQuestAction(requiredItems),
                new WalkQuestAction(UserAreaService.getAreaByName("DrezelPaterdomus")),
                new NPCInteractQuestAction("Drezel", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(),
                new WalkExactTileQuestAction(ThreeLogTile),
                new InventoryInteractQuestAction("Druidic spell", "Cast", a -> !Players.getLocal().isAnimating()),
                new InteractQuestAction("Fungi on log", "Pick", a -> Inventory.contains("Mort myre fungus")),
                new WalkQuestAction(UserAreaService.getAreaByName("NatureGrottoAgility")),
                new InteractQuestAction("Bridge", "Jump", a -> (UserAreaService.getAreaByName("NatureGrotto").contains(Players.getLocal())) && !Players.getLocal().isAnimating()),
                new WalkQuestAction(UserAreaService.getAreaByName("NatureGrotto")),
                new InteractQuestAction("Grotto", "Enter", a -> Dialogues.inDialogue()),
                new NPCInteractQuestAction("Filliman Tarlock", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(),
                new UseItemOnGameObjectQuestAction("Mort myre fungus", 3527, a -> !Inventory.contains("Mort myre fungus")),
                new UseItemOnGameObjectQuestAction("A used spell", 3529, a -> !Inventory.contains("A used spell")),
                new WalkExactTileQuestAction(new Tile(3440, 3335)),
                new NPCInteractQuestAction("Filliman Tarlock", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(3),
                new InteractQuestAction("Grotto", "Enter", a -> (UserAreaService.getAreaByName("NatureGrottoInside").contains(Players.getLocal())) && !Players.getLocal().isAnimating()),
                new InteractQuestAction(3520, "Search", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(),
                createDialogueQuestAction(),
                new NPCInteractQuestAction("Nature spirit", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(),
                new InteractQuestAction(3525, "Exit", a -> !UserAreaService.getAreaByName("NatureGrottoInside").contains(Players.getLocal())),
                new InteractQuestAction("Bridge", "Jump", a -> !(UserAreaService.getAreaByName("NatureGrotto").contains(Players.getLocal())) && !Players.getLocal().isAnimating()),
                new WalkExactTileQuestAction(ThreeLogTile),
                new LoopUntilQuestAction(
                        a -> Inventory.contains(2958),
                        new InventoryInteractQuestAction("Silver sickle (b)", "Cast Bloom", a -> !Players.getLocal().isAnimating()),
                        new InteractQuestAction("Fungi on log", "Pick", a -> GameObjects.closest("Fungi on log") == null),
                        new InventoryInteractQuestAction("Druid pouch", "Fill", a -> !Players.getLocal().isAnimating())
                ),
                new InteractQuestAction("Silver sickle (b)", "Wield", a -> !Inventory.contains("Silver sickle (b)")),
                new LoopUntilQuestAction(
                        a -> false,
                        3,
                        new UseItemOnNPCQuestAction("Druid pouch", "Ghast", a -> !Players.getLocal().isInCombat()),
                        new SimpleCombatQuestAction(5, "Ghast")
                ),
                new WalkQuestAction(UserAreaService.getAreaByName("NatureGrottoAgility")),
                new InteractQuestAction("Bridge", "Jump", a -> (UserAreaService.getAreaByName("NatureGrotto").contains(Players.getLocal())) && !Players.getLocal().isAnimating()),
                new NPCInteractQuestAction("Nature spirit", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(),
                new WaitForQuestCompleteAction()
        ));
    }
}
