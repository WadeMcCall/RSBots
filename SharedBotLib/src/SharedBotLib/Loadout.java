package SharedBotLib;

import com.google.protobuf.SingleFieldBuilderV3;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;

import java.util.*;

public class Loadout {
    public Map<String, Integer> items = new HashMap<>();
    public String head;
    public String cape;
    public String neck;
    public String ammunition;
    public String weapon;
    public String shield;
    public String body;
    public String legs;
    public String hands;
    public String feet;
    public String ring;

    public void getLoadoutFromBank() {
        Logger.log("Getting loadout");

        if (!Bank.isOpen()) {
            Logger.log("SharedBotLib::Loadout: Please open bank before calling getLoadoutFromBank");
            return;
        }


        if (!equipmentMatches()) {
            if (Inventory.isFull())
                Bank.depositAllItems();
            Logger.log("Equipment doesnt match!");
            Bank.depositAllEquipment();
            getItemAndEquip(head);
            getItemAndEquip(cape);
            getItemAndEquip(neck);
            getItemAndEquip(ammunition);
            getItemAndEquip(weapon);
            getItemAndEquip(shield);
            getItemAndEquip(body);
            getItemAndEquip(legs);
            getItemAndEquip(hands);
            getItemAndEquip(feet);
            getItemAndEquip(ring);
            Sleep.sleep((int) Utils.getRandomGuassianDistNotNegative(900,200));
        }
        if (Inventory.contains(item -> !items.containsKey(item.getName()) ||
                (items.get(item.getName()) != -1 && Inventory.count(item.getName()) > items.get(item.getName())))) {
            Bank.depositAllItems();
        }
        Sleep.sleepUntil(() -> equipmentMatches(), 2000);

        items.forEach((key, value) -> {
            Logger.log("Grabbing " + key);
            if (Inventory.count(key) != value){
                if (value == -1) {
                    Bank.withdrawAll(key);
                } else {
                    Bank.withdraw(key, value);
                }
            }
        });

    }

    private boolean equipmentMatches() {
        if (head != null) {
            if (!Equipment.contains(x -> x.getName().contains(head)))
                return false;
        }
        if (cape != null) {
            if (!Equipment.contains(x -> x.getName().contains(cape)))
                return false;
        }
        if (neck != null) {
            if (!Equipment.contains(x -> x.getName().contains(neck)))
                return false;
        }
        if (ammunition != null) {
            if (!Equipment.contains(x -> x.getName().contains(ammunition)))
                return false;
        }
        if (shield != null) {
            if (!Equipment.contains(x -> x.getName().contains(shield)))
                return false;
        }
        if (weapon != null) {
            if (!Equipment.contains(x -> x.getName().contains(weapon)))
                return false;
        }
        if (body != null) {
            if (!Equipment.contains(x -> x.getName().contains(body)))
                return false;
        }
        if (legs != null) {
            if (!Equipment.contains(x -> x.getName().contains(legs)))
                return false;
        }
        if (hands != null) {
            if (!Equipment.contains(x -> x.getName().contains(hands)))
                return false;
        }
        if (feet != null) {
            if (!Equipment.contains(x -> x.getName().contains(feet)))
                return false;
        }
        if (ring != null) {
            return Equipment.contains(x -> x.getName().contains(ring));
        }
        return true;
    }

    public void addViableFoodToLoadout(int minHeal, int amount) {
        Bson foodFilter = Filters.gt("healAmount", minHeal);
        List<FoodItem> foodItems = FoodService.getFoodItemsByFilter(foodFilter);

        foodItems.sort(Comparator.comparingInt(item -> item.getHealAmount()));

        for (FoodItem item : foodItems) {
            if (Bank.contains(item.getName())) {
                items.put(item.getName(), amount);
                return;
            }
        }
    }

    public void addViableFoodToLoadout(int minHeal) {
        int sum = (int) (items.values().stream()
                        .mapToInt(value -> value > 0 ? value : 0) // Use the value if positive, otherwise use 0
                        .sum()
                        + items.values().stream()
                        .filter(value -> value < 0) // Filter to get only negative values
                        .count()); // Count the negative values and add to the sum

        addViableFoodToLoadout(minHeal, 28 - sum);
    }

    private void getItemAndEquip(String itemName) {
        if (itemName == null)
            return;
        Bank.withdraw(x -> x.getName().contains(itemName));
        Sleep.sleepUntil(() -> Inventory.contains(x -> x.getName().contains(itemName)), 1800);
        Inventory.interact(x -> x.getName().contains(itemName), "Wield");
        Inventory.interact(x -> x.getName().contains(itemName), "Wear");
    }

    public boolean hasFullLoadout() {
        if (!equipmentMatches())
            return false;

        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            if (entry.getValue() < 0) {
                continue; // Skip items with a negative value
            }
            if (Inventory.count(entry.getKey()) != entry.getValue()) {
                return false;
            }
        }

        Logger.log("hasFullLoadout is true!");

        return true;
    }
}
