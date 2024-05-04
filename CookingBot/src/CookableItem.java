public class CookableItem {
    private final String name;
    private final int lvlRequirement;

    public CookableItem(String name, int lvlRequirement) {
        this.name = name;
        this.lvlRequirement = lvlRequirement;
    }

    public String getName() {
        return name;
    }

    public int getLvlRequirement() {
        return lvlRequirement;
    }
}
