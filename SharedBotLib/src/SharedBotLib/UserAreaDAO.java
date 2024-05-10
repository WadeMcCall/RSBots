package SharedBotLib;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.conversions.Bson;
import org.dreambot.api.methods.map.Area;
import org.bson.types.ObjectId;
import org.dreambot.api.methods.map.Tile;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Updates.set;

public class UserAreaDAO {
    private final MongoCollection<AreaModel> collection;

    public UserAreaDAO() {
        MongoDatabase database = MongoDBUtil.getDatabase();
        collection = database.getCollection("UserAreas", AreaModel.class);
    }

    // Get all user areas
    public List<AreaModel> getAllUserAreas() {
        return collection.find().into(new ArrayList<>());
    }

    // Add a user area
    public void addUserArea(AreaModel area) {
        collection.insertOne(area);
    }

    // Get a user area by name
    public AreaModel getUserAreaByName(String name) {
        return collection.find(Filters.eq("name", name)).first();
    }

    // Get user areas by filter
    public List<AreaModel> getUserAreasByFilter(Bson filter) {
        return collection.find(filter).into(new ArrayList<>());
    }

    // Update a specific field in a user area document
    public void updateUserAreaField(ObjectId id, Bson update) {
        collection.updateOne(Filters.eq("_id", id), update);
    }

    // Update a user area by name
    public void updateUserAreaByName(String name, Bson update) {
        collection.updateOne(Filters.eq("name", name), update);
    }

    // Update zIndex
    public void updateZIndexByName(String name, Integer zIndex) {
        if (zIndex != null) {
            collection.updateOne(Filters.eq("name", name), set("zIndex", zIndex));
        }
    }

    // Update region
    public void updateRegionByName(String name, AreaModel.Region region) {
        if (region != null) {
            collection.updateOne(Filters.eq("name", name), set("region", region.name()));
        }
    }
}
