import Actions.QuestAction;

import java.util.*;

// I do realize this is simply a shitty wrapper on the Queue class at the moment....
// The intent is to encapsulate checks on whether the quest section is complete using
// quest varbits and varps.
// https://github.com/Zoinkwiz/quest-helper/tree/master/src/main/java/com/questhelper/helpers/quests
// https://github.com/Zoinkwiz/quest-helper/blob/master/src/main/java/com/questhelper/questinfo/QuestVarbits.java
// https://github.com/Zoinkwiz/quest-helper/blob/master/src/main/java/com/questhelper/questinfo/QuestVarPlayer.java
public class QuestSection {
    private final Queue<QuestAction> actions;
    private List<String> requiredItems = new ArrayList<>();


    // Constructor with a defensive copy
    public QuestSection(Queue<QuestAction> _actions) {
        // Create a defensive copy of the queue
        actions = new LinkedList<>(_actions);
    }

    public void setRequiredItems(List<String> items) {
        requiredItems = new ArrayList<>(items);
    }

    public QuestAction getNextQuestAction() {
        return actions.poll();
    }

    public boolean hasActions() {
        return !actions.isEmpty();
    }

    // TODO add a way of checking if this quest section is completed.
}
