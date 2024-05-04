package SharedBotLib;

import org.dreambot.api.methods.skills.Skill;

public class FoodItem {
    private String name;
    private int healAmount;
    private int numUses;
    private int boostAmount;
    private boolean boosts;
    private Skill boostSkill;

    // Constructors
    public FoodItem() {
    }

    public FoodItem(String name, int healAmount, int numUses, int boostAmount, boolean boosts, Skill boostSkill) {
        this.name = name;
        this.healAmount = healAmount;
        this.numUses = numUses;
        this.boostAmount = boostAmount;
        this.boosts = boosts;
        this.boostSkill = boostSkill;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getHealAmount() {
        return healAmount;
    }

    public int getNumUses() {
        return numUses;
    }

    public int getBoostAmount() {
        return boostAmount;
    }

    public boolean isBoosts() {
        return boosts;
    }

    public Skill getBoostSkill() {
        return boostSkill;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    public void setNumUses(int numUses) {
        this.numUses = numUses;
    }

    public void setBoostAmount(int boostAmount) {
        this.boostAmount = boostAmount;
    }

    public void setBoosts(boolean boosts) {
        this.boosts = boosts;
    }

    public void setBoostSkill(Skill boostSkill) {
        this.boostSkill = boostSkill;
    }
}
