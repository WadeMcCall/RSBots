package Actions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LoopUntilQuestAction extends QuestAction {
    private final Predicate<QuestAction> loopCondition;
    private final List<QuestAction> actions;
    private int currentIndex = 0;
    private int iterationCount = 0;
    private final int maxIterations;

    public LoopUntilQuestAction(Predicate<QuestAction> loopCondition, QuestAction... actions) {
        this(loopCondition, 100, actions);
    }

    public LoopUntilQuestAction(Predicate<QuestAction> loopCondition, int maxIterations, QuestAction... actions) {
        super(loopCondition);
        this.loopCondition = loopCondition;
        this.actions = Arrays.asList(actions);
        this.maxIterations = maxIterations;
    }

    @Override
    public ActionResult doAction() {
        if (iterationCount >= maxIterations || loopCondition.test(this)) {
            return ActionResult.FINISH; // Stop looping if max iterations reached or the condition is met
        }

        if (currentIndex < actions.size()) {
            QuestAction currentAction = actions.get(currentIndex);
            ActionResult result = currentAction.doAction();

            if (result == ActionResult.FINISH) {
                currentIndex++; // Move to the next action
                if (currentIndex >= actions.size()) {
                    currentIndex = 0; // Reset index to loop from the beginning
                    iterationCount++; // Increment loop counter after one full cycle
                }
            } else if (result == ActionResult.ERROR) {
                return ActionResult.ERROR; // Stop execution if any action fails
            }

            return ActionResult.CONTINUE; // Continue with the current action or loop
        }

        return ActionResult.CONTINUE; // Continue looping from the start if all actions are cycled through
    }

    @Override
    public boolean isComplete() {
        return loopCondition.test(this) || iterationCount >= maxIterations; // Check if the condition to stop looping is met or max iterations reached
    }
}
