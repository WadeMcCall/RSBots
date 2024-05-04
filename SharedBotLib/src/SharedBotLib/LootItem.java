package SharedBotLib;

public class LootItem {
    public LootItem(String _name, int _priority) {
        name = _name;
        priority = _priority;
    }

    public String getName() {
        return name;
    }

    public final String name;
    public int priority;
}
