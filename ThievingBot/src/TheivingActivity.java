import SharedBotLib.Activity;
import SharedBotLib.FoodItem;
import org.dreambot.api.methods.map.Area;

public abstract class TheivingActivity extends Activity {
    public Area theivingArea;
    public Area bankArea;
    public String npcName;
    public int npcTheivingHit;
    public int numItemsFromTarget;
    public FoodItem currentFoodItem;
    public int minHealingFood;
}
