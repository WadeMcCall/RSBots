import SharedBotLib.Utils;
import org.dreambot.api.input.Keyboard;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.Item;

@ScriptManifest(name = "Gem Bot", description = "Simple gem bot", author = "Gronker",
        version = 1.0, category = Category.CRAFTING, image = "")
public class GemBot extends AbstractScript {

    public String uncutGemName = "Uncut sapphire";
    public String cutGemName = "Sapphire";

    @Override
    public int onLoop() {
        if (Dialogues.canContinue()) {
            Dialogues.continueDialogue();
            return 1;
        }

        if (Bank.isOpen()) {
            if (Inventory.contains(uncutGemName)) {
                Bank.close();
            } else if (Inventory.contains(cutGemName)) {
                Bank.depositAll(cutGemName);
            } else {
                Bank.withdrawAll(uncutGemName);
            }
            return (int)Utils.getRandomGuassianDistNotNegative(200,65);
        }


        if (!Inventory.contains(uncutGemName)) {
            GameObject bank = GameObjects.closest(n -> n.hasAction("Bank"));
            bank.interact("Bank");
            Sleep.sleepUntil(() -> Bank.isOpen(), 4000);
            return (int)Utils.getRandomGuassianDistNotNegative(300,100);
        }

        if (!Players.getLocal().isAnimating()
                && !Dialogues.inDialogue()) {
            Sleep.sleep((int)Utils.getRandomGuassianDistNotNegative(300,15));
            if (Players.getLocal().isAnimating()) {
                return 0;
            }
            Item chisel = Inventory.get("Chisel");
            chisel.useOn(uncutGemName);
            Sleep.sleepUntil(() -> Dialogues.inDialogue(), 2000);
            Keyboard.type(" ", false);
            Sleep.sleepUntil(() -> Players.getLocal().isAnimating(), 2000);
        }

        return (int)Utils.getRandomGuassianDistNotNegative(300,100);
    }
}