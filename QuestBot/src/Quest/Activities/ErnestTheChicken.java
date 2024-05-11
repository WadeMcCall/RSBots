package Quest.Activities;

import Quest.Actions.*;
import SharedBotLib.UserAreaService;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;

public class ErnestTheChicken extends QuestActivity{

    public ErnestTheChicken() {
        sections.add(createQuestSection(
                new WalkQuestAction(UserAreaService.getAreaByName("DraynorManorGroundsEntrance")),
                new NPCInteractQuestAction("Veronica", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(1) // Goes through dialogue and chooses the options passed, in order
        ));
        sections.add(createQuestSection(
                new WalkQuestAction(UserAreaService.getAreaByName("DraynorManorEntrance")),
                new InteractQuestAction("Large door", "Open", a -> UserAreaService.getAreaByName("DraynorManorFoyer").contains(Players.getLocal())),
                new WalkQuestAction(UserAreaService.getAreaByName("DraynorManorPoisonRoom")),
                new GroundItemInteractQuestAction("Poison", "Take", a -> Inventory.contains("Poison")),
                new WalkQuestAction(UserAreaService.getAreaByName("DraynorManorFishfood")),
                new GroundItemInteractQuestAction("Fish food", "Take", a -> Inventory.contains("Fish food")),
                new UseItemQuestAction("Fish food", "Poison", a -> Inventory.contains("Poisoned fish food")),
                new WalkQuestAction(UserAreaService.getAreaByName("DraynorManorBookcaseArea")),
                new InteractQuestAction("Bookcase", "Search", a -> Players.getLocal().getTile().equals(new Tile(3096, 3359, 0)) || Players.getLocal().getTile().equals(new Tile(3096, 3358, 0))),
                new InteractQuestAction("Ladder", "Climb-down", a -> Players.getLocal().getTile().equals(new Tile(3117, 9753, 0))
        )));


        sections.add((createQuestSection(
                new InteractQuestAction("Lever B","Pull" , a -> PlayerSettings.getBitValue(1789) == 1 ),
                new InteractQuestAction("Lever A","Pull" , a -> PlayerSettings.getBitValue(1788) == 1),
                new InteractQuestAction(144, "Open", a -> Players.getLocal().getTile().equals(new Tile(3108, 9759, 0)) && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating()),
                new InteractQuestAction("Lever D","Pull", a -> PlayerSettings.getBitValue(1791) == 1),
                new InteractQuestAction(144, "Open", a -> Players.getLocal().getTile().equals(new Tile(3108, 9757, 0)) && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating()),
                new InteractQuestAction("Lever B","Pull", a -> PlayerSettings.getBitValue(1789) == 0),
                new InteractQuestAction("Lever A","Pull", a -> PlayerSettings.getBitValue(1788) == 0),
                new InteractQuestAction(145, "Open", a -> Players.getLocal().getTile().equals(new Tile(3102, 9759, 0)) && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating()),
                new InteractQuestAction(140, "Open", a -> Players.getLocal().getTile().equals(new Tile(3099, 9760, 0)) && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating()),
                new InteractQuestAction(143, "Open", a -> Players.getLocal().getTile().equals(new Tile(3097, 9764, 0)) && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating()),
                new InteractQuestAction("Lever E","Pull", a -> PlayerSettings.getBitValue(1792) == 1),
                new InteractQuestAction("Lever F","Pull", a -> PlayerSettings.getBitValue(1793) == 1),
                new InteractQuestAction(138, "Open", a -> Players.getLocal().getTile().equals(new Tile(3101, 9765, 0)) && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating()),
                new InteractQuestAction(137, "Open", a -> Players.getLocal().getTile().equals(new Tile(3106, 9765, 0)) && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating()),
                new InteractQuestAction("Lever C","Pull", a -> PlayerSettings.getBitValue(1790) == 1 ),
                new InteractQuestAction(137, "Open", a -> Players.getLocal().getTile().equals(new Tile(3104, 9765, 0)) && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating()),
                new InteractQuestAction(138, "Open", a -> Players.getLocal().getTile().equals(new Tile(3099, 9765, 0)) && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating()),
                new InteractQuestAction("Lever E","Pull", a -> PlayerSettings.getBitValue(1792) == 0 ),
                new InteractQuestAction(138, "Open", a -> Players.getLocal().getTile().equals(new Tile(3101, 9765, 0)) && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating()),
                new InteractQuestAction(142, "Open", a -> Players.getLocal().getTile().equals(new Tile(3102, 9762, 0)) && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating()),
                new InteractQuestAction(145, "Open", a -> Players.getLocal().getTile().equals(new Tile(3102, 9757, 0)) && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating()),
                new InteractQuestAction(141, "Open", a -> Players.getLocal().getTile().equals(new Tile(3099, 9755, 0)) && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating()),
                new GroundItemInteractQuestAction("Oil can", "Take", a -> Inventory.contains("Oil can")),
                new InteractQuestAction(141, "Open", a -> Players.getLocal().getTile().equals(new Tile(3101, 9755, 0)) && !Players.getLocal().isMoving() && !Players.getLocal().isAnimating()),
                new InteractQuestAction("Ladder", "Climb-up", a -> UserAreaService.getAreaByName("DraynorManorSecretRoom").contains(Players.getLocal())),
                new InteractQuestAction("Lever","Pull", a -> UserAreaService.getAreaByName("DraynorManorBookcaseArea").contains(Players.getLocal()))
        )));

        sections.add((createQuestSection(
                new WalkQuestAction(UserAreaService.getAreaByName("DraynorManorExitRoom")),
                new GroundItemInteractQuestAction("Spade", "Take", a -> Inventory.contains("Spade")),
                new WalkQuestAction(UserAreaService.getAreaByName("DraynorManorCompostHeap")),
                new InteractQuestAction("Compost heap", "Search", a -> Inventory.contains("Key")),
                new WalkQuestAction(UserAreaService.getAreaByName("DraynorManorFountain")),
                new UseItemOnGameObjectQuestAction("Poisoned fish food", "Fountain", a -> !Inventory.contains("Poisoned fish food")),
                new InteractQuestAction("Fountain", "Search", a -> Inventory.contains("Pressure gauge")),
                new WalkQuestAction(UserAreaService.getAreaByName("DraynorManorPressureGaugeRoom")),
                new GroundItemInteractQuestAction("Rubber tube", "Take", a -> Inventory.contains("Rubber tube")),
                new WalkQuestAction(UserAreaService.getAreaByName("DraynorManorProfessorOddenstien")),
                new NPCInteractQuestAction("Professor Oddenstein", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(1, 2), // Goes through dialogue and chooses the options passed, in order
                new NPCInteractQuestAction("Professor Oddenstein", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(),
                new WaitForQuestCompleteAction()
        )));

    }
}

