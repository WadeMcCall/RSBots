package SharedBotLib;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import org.bson.conversions.Bson;
import org.dreambot.api.methods.skills.Skill;

import java.util.ArrayList;
import java.util.List;

public class FoodItemDAO {
    private final MongoCollection<Document> collection;

    public FoodItemDAO() {
        MongoDatabase database = MongoDBUtil.getDatabase();
        this.collection = database.getCollection("FoodItems");
    }

    public void addFoodItem(FoodItem item) {
        Document doc = new Document("name", item.getName())
                .append("healAmount", item.getHealAmount())
                .append("numUses", item.getNumUses())
                .append("boosts", item.isBoosts())
                .append("boostAmount", item.getBoostAmount())
                .append("boostSkill", item.getBoostSkill() != null ? item.getBoostSkill().name() : null);
        collection.insertOne(doc);
    }

    public List<FoodItem> getAllFoodItems() {
        List<FoodItem> foodItems = new ArrayList<>();
        FindIterable<Document> documents = collection.find();
        try (MongoCursor<Document> cursor = documents.iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                FoodItem item = documentToFoodItem(doc);
                foodItems.add(item);
            }
        }
        return foodItems;
    }

    private FoodItem documentToFoodItem(Document doc) {
        FoodItem item = new FoodItem();

        item.setName(doc.getString("name"));  // Typically safe to assume 'name' is always present, adjust if not.

        // Check and set healAmount
        Integer healAmount = doc.getInteger("healAmount");
        if (healAmount != null) {
            item.setHealAmount(healAmount);
        }

        // Check and set numUses
        Integer numUses = doc.getInteger("numUses");
        if (numUses != null) {
            item.setNumUses(numUses);
        }

        // Check and set boostAmount
        Integer boostAmount = doc.getInteger("boostAmount");
        if (boostAmount != null) {
            item.setBoostAmount(boostAmount);
        }

        // Check and set boosts
        boolean boosts = doc.getBoolean("boosts", false);  // Defaults to false if not present
        item.setBoosts(boosts);

        // Check and set boostSkill
        String boostSkillStr = doc.getString("boostSkill");
        if (boostSkillStr != null && !boostSkillStr.isEmpty()) {
            try {
                Skill boostSkill = Skill.valueOf(boostSkillStr);
                item.setBoostSkill(boostSkill);
            } catch (IllegalArgumentException e) {
                // Handle the case where the skill name does not match any enum constants
                item.setBoostSkill(null);  // or handle the error appropriately
            }
        }

        return item;
    }

    public List<FoodItem> getFoodItemsByFilter(Bson filter) {
        List<FoodItem> foodItems = new ArrayList<>();
        FindIterable<Document> documents = collection.find(filter);
        try (MongoCursor<Document> cursor = documents.iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                FoodItem item = documentToFoodItem(doc);
                foodItems.add(item);
            }
        }
        return foodItems;
    }
}
