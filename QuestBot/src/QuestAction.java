import java.util.function.Predicate;

public abstract class QuestAction {
    public static enum ActionResult {
        CONTINUE,
        FINISH,
        ERROR
    }

    public boolean isComplete() {
        return completionCheck.test(this);
    }

    public void setCompletionCheck(Predicate<QuestAction> completionCheck) {
        this.completionCheck = completionCheck;
    }


    public QuestAction() {}
    public QuestAction(Predicate<QuestAction> completionCheck) {
        this.completionCheck = completionCheck;
    }

    protected Predicate<QuestAction> completionCheck = action -> true; // Default predicate (always returns false)
    public abstract ActionResult doAction();
}
