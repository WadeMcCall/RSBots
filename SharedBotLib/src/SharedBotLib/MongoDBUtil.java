package SharedBotLib;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
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
                // Create a CodecRegistry with the custom AreaModelCodec
                CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                        MongoClientSettings.getDefaultCodecRegistry(),
                        CodecRegistries.fromCodecs(new AreaModelCodec())
                );

                // Build MongoClientSettings with the custom CodecRegistry
                MongoClientSettings settings = MongoClientSettings.builder()
                        .codecRegistry(codecRegistry)
                        .build();

                // Initialize the MongoClient and MongoDatabase
                mongoClient = MongoClients.create(settings);
                database = mongoClient.getDatabase("WadeBot");
            }
        } catch (Exception e) {
            Logger.log(Logger.LogType.INFO, e.getMessage());  // Log the exception or handle it as appropriate
            throw new RuntimeException("Failed to connect to MongoDB", e);
        }
        return database;
    }
}