package SharedBotLib;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.dreambot.api.utilities.Logger;

public class MongoDBUtil {
    private static MongoClient mongoClient = null;
    private static MongoDatabase database = null;

    private MongoDBUtil() {
        // Private constructor to prevent instantiation
    }

    public static MongoDatabase getDatabase() {
        try{
            if (mongoClient == null) {
                mongoClient = MongoClients.create("mongodb://localhost:27017");
                database = mongoClient.getDatabase("WadeBot");
            }
        } catch (Exception e) {
            Logger.log(Logger.LogType.INFO, e.getMessage());  // Log the exception or handle it as appropriate
            throw new RuntimeException("Failed to connect to MongoDB", e);
        }
        return database;
    }
}