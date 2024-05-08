public abstract class QuestAction {
    public static enum ActionResult {
        CONTINUE,
        FINISH,
        ERROR
    }

    public abstract ActionResult doAction();
}
