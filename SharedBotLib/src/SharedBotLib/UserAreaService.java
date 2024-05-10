package SharedBotLib;

import org.bson.conversions.Bson;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;

import java.util.List;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Updates.*;

public class UserAreaService {
    private static final UserAreaDAO userAreaDAO = new UserAreaDAO();

    // Returns all user areas as AreaModel objects
    public static List<AreaModel> getAllUserAreas() {
        return userAreaDAO.getAllUserAreas();
    }

    // Returns all user areas directly as DreamBot Area objects
    public static List<Area> getAllAreas() {
        return getAllUserAreas().stream().map(AreaModel::toArea).collect(Collectors.toList());
    }

    public static void addUserArea(AreaModel area) {
        userAreaDAO.addUserArea(area);
    }

    // Returns an AreaModel object by name
    public static AreaModel getUserAreaByName(String name) {
        return userAreaDAO.getUserAreaByName(name);
    }

    // Returns the corresponding DreamBot Area object by name
    public static Area getAreaByName(String name) {
        AreaModel areaModel = getUserAreaByName(name);
        return areaModel != null ? areaModel.toArea() : null;  // Return null if not found
    }

    public static List<AreaModel> getUserAreasByFilter(Bson filter) {
        return userAreaDAO.getUserAreasByFilter(filter);
    }

    // Returns a filtered list of DreamBot Area objects
    public static List<Area> getAreasByFilter(Bson filter) {
        return getUserAreasByFilter(filter).stream().map(AreaModel::toArea).collect(Collectors.toList());
    }


    // Update existing user areas with z-index
    public static void updateZIndex(String name, Integer zIndex) {
        userAreaDAO.updateZIndexByName(name, zIndex);
    }

    // Update existing user areas with region
    public static void updateRegion(String name, AreaModel.Region region) {
        userAreaDAO.updateRegionByName(name, region);
    }

    // Update multiple existing areas with a z-index
    public static void updateMultipleZIndices(List<String> names, List<Integer> zIndices) {
        if (names.size() != zIndices.size()) {
            throw new IllegalArgumentException("Names and zIndices lists must have the same length");
        }

        for (int i = 0; i < names.size(); i++) {
            updateZIndex(names.get(i), zIndices.get(i));
        }
    }

    // Update area by name with new DreamBot Area
    public static void updateAreaByName(String name, Area newArea) {
        AreaModel existingAreaModel = userAreaDAO.getUserAreaByName(name);
        if (existingAreaModel != null) {
            Bson topLeftUpdate = combine(
                    set("topLeft.x", newArea.getBoundaryPoints().get(0).getX()),
                    set("topLeft.y", newArea.getBoundaryPoints().get(0).getY())
            );

            Bson bottomRightUpdate = combine(
                    set("bottomRight.x", newArea.getBoundaryPoints().get(1).getX()),
                    set("bottomRight.y", newArea.getBoundaryPoints().get(1).getY())
            );

            Bson zIndexUpdate = set("zIndex", newArea.getZ());

            Bson update = combine(topLeftUpdate, bottomRightUpdate, zIndexUpdate);

            userAreaDAO.updateUserAreaByName(name, update);
        } else {
            // Optionally, create a new AreaModel if the area doesn't exist
            AreaModel newAreaModel = new AreaModel(name, newArea);
            userAreaDAO.addUserArea(newAreaModel);
        }
    }
}
