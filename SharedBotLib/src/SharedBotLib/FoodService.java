package SharedBotLib;

import org.bson.conversions.Bson;

import java.util.List;

public class FoodService {
    private static final FoodItemDAO foodItemDAO = new FoodItemDAO();

    public static List<FoodItem> getAllFoodItems() {
        return foodItemDAO.getAllFoodItems();
    }

    public static void addFoodItem(FoodItem item) {
        foodItemDAO.addFoodItem(item);
    }

    public static List<FoodItem> getFoodItemsByFilter(Bson filter){
        return foodItemDAO.getFoodItemsByFilter(filter);
    }
}
