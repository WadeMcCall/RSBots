import SharedBotLib.Activity;
import SharedBotLib.State;
import SharedBotLib.StateMachine;
import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.widgets.message.Message;

public class EntryState extends State<Activity> {

    public EntryState(StateMachine<Activity> sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        findEntryState();
    }

    private void findEntryState() {
        Widget parent = Widgets.getWidget(GuardiansWidgetTextureIDs.WIDGET_PARENT_ID);
        if (parent == null) {
            state_machine.switchState(GuardiansStateMachine.States.PRE_GAME);
            return;
        } else if (parent.getChild(GuardiansWidgetTextureIDs.ELEMENTAL_CHILD_WIDGET_ID).getTextureId() == GuardiansWidgetTextureIDs.ELEMENTAL_TEXTURE_ID) {
            state_machine.switchState(GuardiansStateMachine.States.MINING_FRAGMENTS_PRE_GAME);
            return;
        }
        state_machine.switchState(GuardiansStateMachine.States.WALKING_TO_WORKBENCH);
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
