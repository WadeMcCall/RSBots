package SharedBotLib;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.BsonType;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.dreambot.api.methods.map.Tile;
import SharedBotLib.AreaModel.Region;

public class AreaModelCodec implements Codec<AreaModel> {

    @Override
    public AreaModel decode(BsonReader reader, DecoderContext decoderContext) {
        String name = null;
        Tile topLeft = null;
        Tile bottomRight = null;
        Integer zIndex = null; // Optional field
        Region region = null; // Optional field

        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String fieldName = reader.readName();
            switch (fieldName) {
                case "name":
                    name = reader.readString();
                    break;
                case "topLeft":
                    reader.readStartDocument();
                    int x1 = reader.readInt32("x");
                    int y1 = reader.readInt32("y");
                    topLeft = new Tile(x1, y1);
                    reader.readEndDocument();
                    break;
                case "bottomRight":
                    reader.readStartDocument();
                    int x2 = reader.readInt32("x");
                    int y2 = reader.readInt32("y");
                    bottomRight = new Tile(x2, y2);
                    reader.readEndDocument();
                    break;
                case "zIndex":
                    if (reader.getCurrentBsonType() != BsonType.NULL) {
                        zIndex = reader.readInt32();
                    } else {
                        reader.readNull();
                    }
                    break;
                case "region":
                    if (reader.getCurrentBsonType() != BsonType.NULL) {
                        region = Region.valueOf(reader.readString());
                    } else {
                        reader.readNull();
                    }
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.readEndDocument();

        return new AreaModel(name, topLeft, bottomRight, zIndex, region);
    }

    @Override
    public void encode(BsonWriter writer, AreaModel areaModel, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeString("name", areaModel.getName());

        writer.writeName("topLeft");
        writer.writeStartDocument();
        writer.writeInt32("x", areaModel.getTopLeft().getX());
        writer.writeInt32("y", areaModel.getTopLeft().getY());
        writer.writeEndDocument();

        writer.writeName("bottomRight");
        writer.writeStartDocument();
        writer.writeInt32("x", areaModel.getBottomRight().getX());
        writer.writeInt32("y", areaModel.getBottomRight().getY());
        writer.writeEndDocument();

        if (areaModel.getZIndex() != null) {
            writer.writeInt32("zIndex", areaModel.getZIndex());
        } else {
            writer.writeNull("zIndex");
        }

        if (areaModel.getRegion() != null) {
            writer.writeString("region", areaModel.getRegion().name());
        } else {
            writer.writeNull("region");
        }

        writer.writeEndDocument();
    }

    @Override
    public Class<AreaModel> getEncoderClass() {
        return AreaModel.class;
    }
}
