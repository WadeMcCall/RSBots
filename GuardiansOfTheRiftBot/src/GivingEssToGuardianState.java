import SharedBotLib.Activity;
import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.Utils;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.widgets.WidgetChild;
import org.dreambot.api.wrappers.widgets.message.Message;

public class GivingEssToGuardianState extends State<Activity> {
    public GivingEssToGuardianState(StateMachine<Activity> sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Dialogues.canContinue()){
            if (Dialogues.getNPCDialogue().contains("resting")) {
                state_machine.switchState(GuardiansStateMachine.States.PRE_GAME);
                return;
            }
            Dialogues.continueDialogue();
        }

        if (!Inventory.contains("Elemental guardian stone") && !Inventory.contains("Catalytic guardian stone")) {
            state_machine.switchState(GuardiansStateMachine.States.DEPOSITING_IN_POOL);
            return;
        }

        NPCs.closest("The Great Guardian").interact();
        Sleep.sleepUntil(() -> !Inventory.contains(n -> n.getName().contains("guardian stone")), 8000);
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {
        if (message.getMessage().contains(GuardiansWidgetTextureIDs.gameEndedText) || message.getMessage().contains(GuardiansWidgetTextureIDs.gameLostText)) {
            Sleep.sleep((int) Utils.getRandomGuassianDistNotNegative(3000, 800));
            state_machine.switchState(GuardiansStateMachine.States.PRE_GAME);
        }
    }
}
