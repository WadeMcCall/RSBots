import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.wrappers.widgets.WidgetChild;

import java.util.function.Predicate;

public class WidgetInteractQuestAction extends QuestAction{
    public int widgetParentID;
    public int widgetChildID;
    public String action;

    WidgetInteractQuestAction(int _widgetParent, int _widgetChild, String _action, Predicate<WidgetInteractQuestAction> completionCheck) {
        super(a -> completionCheck.test((WidgetInteractQuestAction) a));

        widgetParentID = _widgetParent;
        widgetChildID = _widgetChild;
        action = _action;
    }

    @Override
    public ActionResult doAction() {
        WidgetChild widgetChild = Widgets.getWidget(widgetParentID).getChild(widgetChildID);

        if (widgetChild == null)
            return ActionResult.FINISH;

        widgetChild.interact(action);

        if (isComplete() || !widgetChild.isVisible()) {
            return ActionResult.FINISH;
        }

        return ActionResult.CONTINUE;
    }
}
