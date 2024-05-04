import SharedBotLib.Activity;
import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.UserAreas;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.widgets.message.Message;

import java.util.ArrayList;
import java.util.List;

public class CraftingRunesState extends State<Activity> {
    public CraftingRunesState(StateMachine<Activity> sm) {
        super(sm);
    }

    private List<Item> pouches = new ArrayList<Item>();
    int numEmptyPouches;

    @Override
    public void doAction() {
        if (Dialogues.canContinue())
            Dialogues.continueDialogue();

        Player me = Players.getLocal();

        if (UserAreas.GuardiansFullArea.contains(me)) {
            state_machine.switchState(GuardiansStateMachine.States.GIVING_ESS_TO_GUARDIAN);
            Sleep.sleep(300, 1000);
            return;
        }

        if (Inventory.contains(x -> x.getName().contains("talisman"))) {
            Inventory.get(x -> x.getName().contains("talisman")).interact("drop");
            Sleep.sleep(200,800);
            return;
        }

        if (!Inventory.contains("Guardian essence")) {
            if (emptyPouches()) {
                return;
            }
            GameObjects.closest("Portal").interact();
            Sleep.sleepUntil(() -> UserAreas.GuardiansFullArea.contains(me), 7000);
            Sleep.sleep(600, 1200);
            return;
        }

        GameObject altar = GameObjects.closest("Altar");

        if (altar != null){
            altar.interact();
            Sleep.sleepUntil(() -> !Inventory.contains("Guardian essence"), 7000);
            return;
        }
    }

    private boolean emptyPouches() {
        if (pouches.size() > numEmptyPouches) {
            Item pouch = pouches.get(numEmptyPouches);
            while (hasInvForEss(pouch)) {
                pouch.interact("Empty");
                numEmptyPouches++;
                if(numEmptyPouches >= pouches.size())
                    break;
                pouch = pouches.get(numEmptyPouches);
            }
            Sleep.sleep(600, 1000);
            return true;
        }
        return false;
    }

    private boolean hasInvForEss(Item pouch) {

        switch(pouch.getName()) {
            case "Small pouch":
                if (Inventory.getEmptySlots() >= 3)
                    return true;
                break;
            case "Medium pouch":
                if (Inventory.getEmptySlots() >= 6)
                    return true;
                break;
            case "Large pouch":
                if (Inventory.getEmptySlots() >= 9)
                    return true;
                break;
            case "Giant pouch":
                if (Inventory.getEmptySlots() >= 12)
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
        numEmptyPouches = 0;
    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {
        if (message.getMessage().contains(GuardiansWidgetTextureIDs.gameEndedText) || message.getMessage().contains(GuardiansWidgetTextureIDs.gameLostText)) {
            Sleep.sleep(600,5000);
            state_machine.switchState(GuardiansStateMachine.States.PRE_GAME);
        }
    }
}
