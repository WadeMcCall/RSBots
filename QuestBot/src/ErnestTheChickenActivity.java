import SharedBotLib.UserAreas;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.settings.PlayerSettings;

import java.util.*;

public class ErnestTheChickenActivity extends QuestActivity{

    public ErnestTheChickenActivity() {
        sections.add(createQuestSection(
                new WalkQuestAction(UserAreas.DraynorManorGroundsEntrance),
                new NPCInteractQuestAction("Veronica", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(1) // Goes through dialogue and chooses the options passed, in order
        ));
        sections.add(createQuestSection(
                new WalkQuestAction(UserAreas.DraynorManorEntrance),
                new InteractQuestAction("Large door", "Open", a -> UserAreas.DraynorManorFoyer.contains(Players.getLocal())),
                new WalkQuestAction(UserAreas.DraynorManorPoisonRoom),
                new GroundItemInteractQuestAction("Poison", "Take", a -> Inventory.contains("Poison")),
                new WalkQuestAction(UserAreas.DraynorManorFishfood),
                new GroundItemInteractQuestAction("Fish food", "Take", a -> Inventory.contains("Fish food")),
                new UseItemQuestAction("Fish food", "Poison", a -> Inventory.contains("Poisoned fish food")),
                new WalkQuestAction(UserAreas.DraynorManorBookcaseArea),
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
                new InteractQuestAction("Ladder", "Climb-up"),
                new InteractQuestAction("Lever","Pull" )
        )));

        sections.add((createQuestSection(
                new WalkQuestAction(UserAreas.DraynorManorExitRoom),
                new GroundItemInteractQuestAction("Spade", "Take", a -> Inventory.contains("Spade")),
                new WalkQuestAction(UserAreas.DraynorManorCompostHeap),
                new InteractQuestAction("Compost heap", "Search", a -> Inventory.contains("Key")),
                new WalkQuestAction(UserAreas.DraynorManorFountain),
                new UseItemOnGameObjectQuestAction("Poisoned fish food", "Fountain",a -> !Inventory.contains("Poisoned fish food")),
                new InteractQuestAction("Fountain", "Search", a -> Inventory.contains("Pressure gauge")),
                new WalkQuestAction(UserAreas.DraynorManorPressureGaugeRoom),
                new GroundItemInteractQuestAction("Rubber tube", "Take", a -> Inventory.contains("Rubber tube")),
                new WalkQuestAction(UserAreas.DraynorManorProfessorOddenstien),
                new NPCInteractQuestAction("Professor Oddenstein", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(1, 2), // Goes through dialogue and chooses the options passed, in order
                new NPCInteractQuestAction("Professor Oddenstein", "Talk-to", a -> Dialogues.inDialogue()),
                createDialogueQuestAction(),
                new WaitForQuestCompleteAction()
        )));

    }
}

