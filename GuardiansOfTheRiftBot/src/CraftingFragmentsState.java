import SharedBotLib.Activity;
import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.Utils;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.widgets.message.Message;

import java.util.ArrayList;
import java.util.List;

public class CraftingFragmentsState extends State<Activity> {
    public CraftingFragmentsState(StateMachine<Activity> sm) {
        super(sm);
    }

    private List<Item> pouches = new ArrayList<Item>();
    int numFullPouches;

    @Override
    public void doAction() {
        if (Dialogues.canContinue()) {
            Dialogues.continueDialogue();
            Sleep.sleepUntil(() -> !Dialogues.inDialogue(), 600);
            state_machine.switchState(GuardiansStateMachine.States.WALKING_TO_WORKBENCH);
            return;
        }

        Player me = Players.getLocal();
        if (Inventory.isFull()) {
            if (fillPouches()) {
                return;
            }
            state_machine.switchState(GuardiansStateMachine.States.FINDING_GUARDIAN);
            return;
        }

        if (!Inventory.contains("Guardian fragments")) {
            state_machine.switchState(GuardiansStateMachine.States.MINING_DURING_GAME);
            return;
        }
    }

    private boolean fillPouches() {
        if (pouches.size() > numFullPouches) {
            Item pouch = pouches.get(numFullPouches);
            while (hasEssenceForPouch(pouch)) {
                pouch.interact();
                numFullPouches++;
                if(numFullPouches >= pouches.size())
                    break;
                pouch = pouches.get(numFullPouches);
            }
            Sleep.sleep((int) Utils.getRandomGuassianDistNotNegative(300, 100));
            GameObjects.closest("Workbench").interact();
            return true;
        }
        return false;
    }

    private boolean hasEssenceForPouch(Item pouch) {
        switch(pouch.getName()) {
            case "Small pouch":
                if (Inventory.count("Guardian essence") >= 3)
                    return true;
                break;
            case "Medium pouch":
                if (Inventory.count("Guardian essence") >= 6)
                    return true;
                break;
            case "Large pouch":
                if (Inventory.count("Guardian essence") >= 9)
                    return true;
                break;
            case "Giant pouch":
                if (Inventory.count("Guardian essence") >= 12)
                    return true;
                break;
            case "Colossal pouch":
                // TODO
                return true;
        }
        return false;
    }

    @Override
    public void Enter() {
        pouches = Inventory.all(n -> n.getName().contains("pouch"));
        numFullPouches = 0;
    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {
        if (message.getMessage().contains(GuardiansWidgetTextureIDs.gameEndedText)) {
            Sleep.sleep((int)Utils.getRandomGuassianDistNotNegative(3000, 800));
            state_machine.switchState(GuardiansStateMachine.States.PRE_GAME);
        }
    }
}
