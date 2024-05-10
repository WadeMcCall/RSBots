package SharedBotLib;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.bson.types.ObjectId;

import java.util.List;

public class AreaModel {
    private ObjectId id;
    private String name;
    private Tile topLeft;
    private Tile bottomRight;
    private Integer zIndex; // Optional z-index
    private Region region; // Optional region enum

    // Enum for specifying the region
    public enum Region {
        LUMBRIDGE,
        VARROCK,
        FALADOR,
        DRAYNOR,
        ALKHARID,
        EDGEVILLE,
        GOTR,
        SEERS,
        CANIFIS,
        ARDOUGNE
    }

    // Constructors
    public AreaModel(String name, Tile topLeft, Tile bottomRight) {
        this(name, topLeft, bottomRight, topLeft.getZ(), null);
    }

    public AreaModel(String name, int x1, int y1, int x2, int y2) {
        this(name, new Tile(Math.min(x1, x2), Math.min(y1, y2)), new Tile(Math.max(x1, x2), Math.max(y1, y2)), 0, null);
    }

    public AreaModel(String name, Area area) {
        this(name, area, area.getZ(), null);
    }

    // Constructor with all fields
    public AreaModel(String name, Tile topLeft, Tile bottomRight, Integer zIndex, Region region) {
        this.name = name;
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.zIndex = zIndex;
        this.region = region;
    }

    public AreaModel(String name, Area area, Integer zIndex, Region region) {
        List<Tile> boundingTiles = area.getBoundaryPoints();
        this.name = name;
        this.topLeft = boundingTiles.get(0);
        this.bottomRight = boundingTiles.get(1);
        this.zIndex = zIndex;
        this.region = region;
    }

    // Getters and Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tile getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Tile topLeft) {
        this.topLeft = topLeft;
    }

    public Tile getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(Tile bottomRight) {
        this.bottomRight = bottomRight;
    }

    public Integer getZIndex() {
        return zIndex;
    }

    public void setZIndex(Integer zIndex) {
        this.zIndex = zIndex;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Area toArea() {
        return new Area(topLeft.getX(), topLeft.getY(), bottomRight.getX(), bottomRight.getY(), zIndex == null ? 0 : zIndex);
    }
}
