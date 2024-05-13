package Quest.Activities;

import Quest.Actions.*;
import SharedBotLib.UserAreaService;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.container.impl.equipment.EquipmentSlot;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;

import java.util.HashMap;
import java.util.Map;

public class FairyTalePt1 extends QuestActivity {
    public FairyTalePt1() {
        Map<String, Integer> requiredItems = new HashMap<>();
        Map<String, Integer> conditionalItems = new HashMap<>();
        conditionalItems.put("Coins", 5);
        conditionalItems.put("Secateurs", 1);
        conditionalItems.put("Draynor skull", 1);
        conditionalItems.put("Spade", 1);
        conditionalItems.put("Amulet of Glory (6)", 1);
        requiredItems.put("Ghostspeak amulet", 1);
        requiredItems.put("Dramen staff", 1);

        sections.add(createQuestSection(
                new WalkQuestAction(UserAreaService.getAreaByName("DraynorBank")),
                new GatherRequirementsQuestAction(requiredItems, conditionalItems)
        ));
        sections.add(createQuestSection(
                new WalkQuestAction(UserAreaService.getAreaByName("DraynorPigs")),
                new NPCInteractQuestAction("Martin the Master Gardener", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(2, 1, 1, 1),
                new WalkQuestAction((UserAreaService.getAreaByName("FaladorAllotment"))),
                new NPCInteractQuestAction("Elstan", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(1),
                new ConditionalQuestAction(a -> !Inventory.contains("Secateurs"),
                        new WalkQuestAction((UserAreaService.getAreaByName("SarahFarmShop"))),
                        new NPCInteractQuestAction("Sarah", "Trade", a -> Shop.isOpen()),
                        new BuyQuestAction("Secateurs", 1, a -> Inventory.contains("Secateurs"))
                ),
                new WalkQuestAction(UserAreaService.getAreaByName("PortSarimSpiritTree")),
                new NPCInteractQuestAction("Frizzy Skernip", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(1),
                new WalkQuestAction(UserAreaService.getAreaByName("LumbyTreePatch")),
                new NPCInteractQuestAction("Fayeth", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(1),
                new WalkQuestAction(UserAreaService.getAreaByName("LumbridgeHopsArea")),
                new NPCInteractQuestAction("Vasquen", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(1),
                new WalkQuestAction(UserAreaService.getAreaByName("VarrockBushPatch")),
                new NPCInteractQuestAction("Dreven", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(1),
                new WalkQuestAction(UserAreaService.getAreaByName("DraynorPigs")),
                new NPCInteractQuestAction("Martin the Master Gardener", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(2)
        ));
        sections.add(createQuestSection(
                new InventoryInteractQuestAction("Dramen staff", "Wield", a -> Equipment.slotContains(EquipmentSlot.WEAPON, 772)),
                new WalkQuestAction(UserAreaService.getAreaByName("ZanarisShed")),
                new ConditionalQuestAction(a -> !Inventory.contains("Spade"),
                        new InteractQuestAction("Door", "Open", a -> !Players.getLocal().getTile().equals(new Tile(3202, 3169))),
                        new InteractQuestAction("Tools", "Take", a -> Dialogues.inDialogue()),
                        createDialogueQuestAction(2)
                ),
                new InteractQuestAction("Door", "Open", a -> !UserAreaService.getAreaByName("ZanarisShed").contains(Players.getLocal())),
                new WalkQuestAction(UserAreaService.getAreaByName("ZanarisThroneRoom")),
                new NPCInteractQuestAction("Fairy Godfather", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(2),
                new WalkQuestAction(UserAreaService.getAreaByName("FairyNuffRoom")),
                new NPCInteractQuestAction("Fairy Nuff", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(),
                new WalkQuestAction(UserAreaService.getAreaByName("DarkWizardsTower")),
                new InteractQuestAction("Staircase", "Climb-up", a -> !UserAreaService.getAreaByName("DarkWizardsTower").contains(Players.getLocal())),
                new InteractQuestAction("Staircase", "Climb-up", a -> UserAreaService.getAreaByName("ZandarHorfyreDarkWizardsTower").contains(Players.getLocal())),
                new NPCInteractQuestAction("Zandar Horfyre", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction()
        ));
        sections.add(createQuestSection(
                new ConditionalQuestAction(a -> !Inventory.contains("Draynor skull"),
                        new WalkExactTileQuestAction(new Tile(3106, 3383)),
                        new InventoryInteractQuestAction("Spade", "Dig", a -> Inventory.contains("Draynor skull"))
                ),
                new WalkQuestAction(UserAreaService.getAreaByName("MaligniusMortifer")),
                new NPCInteractQuestAction("Malignius Mortifer", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(2)
        ));
    }
}
