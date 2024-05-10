import SharedBotLib.UserAreaService;
import org.dreambot.api.methods.dialogues.Dialogues;
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
                new InteractQuestAction("Gate", "Open", a -> Widgets.getWidget(580).isVisible()),
                // TODO use widget quest action, "Enter the swamp."
        ));
    }
}
