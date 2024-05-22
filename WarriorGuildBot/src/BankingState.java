import SharedBotLib.Loadout;
import SharedBotLib.State;
import SharedBotLib.StateMachine;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.widgets.message.Message;

public class BankingState extends State {
    Loadout loadout;

    public BankingState(StateMachine sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (loadout != null && !Bank.isOpen() && loadout.hasFullLoadout()) {
            if (Inventory.contains("Warrior guild token")) {
                state_machine.switchState(WarriorStateMachine.States.WALKING_TO_CYCLOPS);
                return;
            } else {
                state_machine.switchState(WarriorStateMachine.States.WALKING_TO_ANIMATED_ARMOR);
                return;
            }
        }

        if (!Bank.isOpen()) {
            GameObjects.closest(x -> x.hasAction("Bank")).interact("Bank");
            Sleep.sleepUntil(() -> Bank.isOpen(), 5000);
            return;
        }

        int tokenAmount = Inventory.count("Warrior guild token") + Bank.count("Warrior guild token");
        if (tokenAmount >= 1000) {
            loadout = getCyclopsLoadout();
        } else {
            loadout = getAnimatedArmorLoadout();
        }

        loadout.getLoadoutFromBank();
        Sleep.sleepUntil(() -> loadout.hasFullLoadout(), 10000);
        if (loadout.hasFullLoadout()) {
            Bank.close();
        }
    }

    private Loadout getCyclopsLoadout() {
        Loadout cyclopsLoadout = new Loadout();

        cyclopsLoadout.head = "Berserker helm";
        cyclopsLoadout.cape = "Ardougne cloak 1";
        cyclopsLoadout.weapon = "Dragon scimitar";
        cyclopsLoadout.body = "Rune platebody";
        cyclopsLoadout.shield = "Bronze defender";
        cyclopsLoadout.legs = "Rune platelegs";
        cyclopsLoadout.hands = "Combat bracelet";
        cyclopsLoadout.feet = "Rune boots";

        cyclopsLoadout.items.put("Warrior guild token", -1);
        cyclopsLoadout.items.put("Strength potion(3)", 1);
        cyclopsLoadout.items.put("Attack potion(3)", 1);
        cyclopsLoadout.items.put("Defence potion(3)", 1);
        cyclopsLoadout.addViableFoodToLoadout(5);

        return cyclopsLoadout;
    }

    private  Loadout getAnimatedArmorLoadout() {
        Loadout animatedLoadout = new Loadout();

        animatedLoadout.head = "Berserker helm";
        animatedLoadout.cape = "Ardougne cloak 1";
        animatedLoadout.weapon = "Dragon scimitar";
        animatedLoadout.body = "Rune platebody";
        animatedLoadout.shield = "Bronze defender";
        animatedLoadout.legs = "Rune platelegs";
        animatedLoadout.hands = "Combat bracelet";
        animatedLoadout.feet = "Rune boots";
        animatedLoadout.neck = "Amulet of fury";

        animatedLoadout.items.put("Strength potion(3)", 1);
        animatedLoadout.items.put("Attack potion(3)", 1);
        animatedLoadout.items.put("Defence potion(3)", 1);
        animatedLoadout.items.put("Adamant platebody", 1);
        animatedLoadout.items.put("Adamant platelegs", 1);
        animatedLoadout.items.put("Adamant full helm", 1);
        animatedLoadout.addViableFoodToLoadout(10);

        return animatedLoadout;
    }

    @Override
    public void Enter() {
        loadout = null;
    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {

    }
}
