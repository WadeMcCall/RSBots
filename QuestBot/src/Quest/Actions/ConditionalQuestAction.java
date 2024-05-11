package Quest.Actions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ConditionalQuestAction extends QuestAction {
    private final Predicate<QuestAction> condition;
    private final List<QuestAction> actions;
    private int currentIndex = 0;

    public ConditionalQuestAction(Predicate<QuestAction> condition, QuestAction... actions) {
        super(condition);
        this.condition = condition;
        this.actions = Arrays.asList(actions);
    }

    @Override
    public ActionResult doAction() {
        if (condition.test(this)) {
            if (currentIndex < actions.size()) {
                QuestAction currentAction = actions.get(currentIndex);
                ActionResult result = currentAction.doAction();

                if (result == ActionResult.FINISH) {
                    currentIndex++; // Move to the next action
                } else if (result == ActionResult.ERROR) {
                    // Currently ignoring errors.
                }

                return ActionResult.CONTINUE; // Continue with the current action
            }

            return ActionResult.FINISH; // Mark as finished if all actions are completed
        }

        return ActionResult.FINISH; // Skip if the condition is not met
    }

    @Override
    public boolean isComplete() {
        if (condition.test(this)) {
            return currentIndex >= actions.size();
        }
        return true; // Mark as completed if the condition is not met
    }
}
