import SharedBotLib.State;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.widgets.message.Message;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class BankingState extends State<CookingActivity> {

    private final String[] cookedFood = {"Shrimps", "Anchovies", "Sardine", "Herring"};

    private List<CookableItem> rawFoodList = new ArrayList<>();

    public BankingState(CookingStateMachine cookingStateMachine) {
        super(cookingStateMachine);

        rawFoodList.add(new CookableItem("Raw Shrimps", 1));
        rawFoodList.add(new CookableItem("Raw Anchovies", 1));
        rawFoodList.add(new CookableItem("Raw sardine", 1));
        rawFoodList.add(new CookableItem("Raw herring", 5));

        // Sorting the list by cooking level in descending order
        rawFoodList.sort((item1, item2) -> Integer.compare(item2.getLvlRequirement(), item1.getLvlRequirement()));
    }

    @Override
    public void doAction() {
        String[] itemNames = rawFoodList.stream()
                .map(CookableItem::getName)
                .toArray(String[]::new);

        if (Inventory.isFull() && Inventory.contains(itemNames)) {
            state_machine.switchState(CookingStateMachine.States.WALKING_TO_SPOT);
            return;
        }

        if (!Inventory.isEmpty()){
            Bank.depositAllItems();
            return;
        }

        if(GetItemsFromBank()) {
            Sleep.sleepUntil(Inventory::isFull, 2000);
            if(Inventory.isFull()) {
                Sleep.sleep(300);
                Bank.close();
            }
        } else {
            ScriptManager.getScriptManager().stop();
            Tabs.logout();
        }

    }

    private boolean GetItemsFromBank(){
        int cookingLevel = Skills.getRealLevel(Skill.COOKING);

        for (CookableItem item : rawFoodList) {
            if (item.getLvlRequirement() > cookingLevel)
                continue;
            Bank.withdrawAll(item.getName());
            Sleep.sleep(600, 1000);
            if (Inventory.isFull())
                return true;
        }

        return false;
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {

    }
}
