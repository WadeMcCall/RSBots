import SharedBotLib.Loadout;

public class OakLardersActivity extends ConstructionActivity {
    OakLardersActivity() {
        invSetup = new Loadout();
        invSetup.items.put("Teleport to house", -1);
        invSetup.items.put("Hammer", 1);
        invSetup.items.put("Saw", 1);
        invSetup.items.put("Oak plank", 25);
        invSetup.ring = "Ring of dueling";
        buildAction = 2;
        buildSpace = "Larder space";
        builtItemName = "Larder";
        reqName = "Oak plank";
        reqAmt = 8;
    }
}
