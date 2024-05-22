import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.Utils;
import org.dreambot.api.input.Keyboard;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.widgets.WidgetChild;
import org.dreambot.api.wrappers.widgets.message.Message;

public class BuildingState extends State<ConstructionActivity> {
    public BuildingState(StateMachine<ConstructionActivity> sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Inventory.count(state_machine.activity.reqName) < state_machine.activity.reqAmt) {
            state_machine.switchState(ConstructionStateMachine.States.GOING_TO_BANK);
            return;
        }

        GameObject builtItem = GameObjects.closest(state_machine.activity.builtItemName);
        if (builtItem != null) {
            if(Dialogues.canContinue()) {
                Dialogues.continueDialogue();
                return;
            }
            builtItem.interact("Remove");
            Sleep.sleepUntil(() -> Dialogues.inDialogue(), 10000);
            Sleep.sleep((int) Utils.getRandomGuassianDistNotNegative(300,80));
            Keyboard.type("1", false);

            Sleep.sleepUntil(() -> GameObjects.closest(state_machine.activity.builtItemName) == null, 2000);
            Sleep.sleep((int) Utils.getRandomGuassianDistNotNegative(300,80));
        }

        GameObject buildSpace = GameObjects.closest(state_machine.activity.buildSpace);
        if (buildSpace == null) {
            return;
        }

        Widget constructionInterface = Widgets.getWidget(458);

        if (constructionInterface == null) {
            buildSpace.interact("Build");
            Sleep.sleep(800, 1000);
            return;
        }
        Keyboard.type(String.valueOf(state_machine.activity.buildAction), false);
        Sleep.sleepUntil(() -> GameObjects.closest(state_machine.activity.builtItemName) != null, 5000);
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {

    }
}
