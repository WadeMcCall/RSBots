import SharedBotLib.Activity;
import SharedBotLib.StateMachine;
import SharedBotLib.UserAreas;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.methods.widget.Widgets;

public class GuardiansStateMachine extends StateMachine<Activity> {
    GuardiansStateMachine() {
        addState(States.PRE_GAME, new PreGameState(this));
        addState(States.MINING_FRAGMENTS_PRE_GAME, new MiningFragmentsPregameState(this));
        addState(States.WALKING_TO_WORKBENCH, new WalkingToWorkbenchState(this));
        addState(States.CRAFTING_FRAGMENTS, new CraftingFragmentsState(this));
        addState(States.FINDING_GUARDIAN, new FindingGuardianState(this));
        addState(States.CRAFTING_RUNES, new CraftingRunesState(this));
        addState(States.GIVING_ESS_TO_GUARDIAN, new GivingEssToGuardianState(this));
        addState(States.DEPOSITING_IN_POOL, new DepositingInPoolState(this));
        addState(States.MINING_DURING_GAME, new MiningDuringGameState(this));
        addState(States.ENTRY_STATE, new EntryState(this));
        addState(States.FINDING_PORTAL, new FindingPortal(this));
        addState(States.MINING_HUGE_ESS, new MiningHugeEssState(this));
        addState(States.LEAVING_HUGE_ESS_MINE, new LeavingHugeEssMineState(this));

        switchState(States.ENTRY_STATE);
    }

    public enum States {
        PRE_GAME,
        MINING_FRAGMENTS_PRE_GAME,
        WALKING_TO_WORKBENCH,
        CRAFTING_FRAGMENTS,
        FINDING_GUARDIAN,
        CRAFTING_RUNES,
        GIVING_ESS_TO_GUARDIAN,
        DEPOSITING_IN_POOL,
        MINING_DURING_GAME,
        FINDING_PORTAL,
        MINING_HUGE_ESS,
        LEAVING_HUGE_ESS_MINE,
        ENTRY_STATE

    }

    public static boolean isGameStarted() {
        Widget parent = Widgets.getWidget(GuardiansWidgetTextureIDs.WIDGET_PARENT_ID);
        if (!UserAreas.GuardiansFullArea.contains(Players.getLocal()))
            return true;
        if (parent == null || !parent.isVisible())
            return false;
        return true;
    }

    public static boolean isPortalOpen() {
        Widget parent = Widgets.getWidget(GuardiansWidgetTextureIDs.WIDGET_PARENT_ID);
        if (parent == null) {
            return false;
        } else if (parent.getChild(GuardiansWidgetTextureIDs.PORTAL_CHILD_WIDGET_ID).getTextureId() == GuardiansWidgetTextureIDs.PORTAL_TEXTURE_ID
                && parent.getChild(GuardiansWidgetTextureIDs.PORTAL_CHILD_WIDGET_ID).isVisible()) {
            return true;
        }
        return false;
    }
}
