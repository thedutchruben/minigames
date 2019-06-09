package dev.thedutchruben.core.framework.database.adabters;

import com.google.gson.*;
import dev.thedutchruben.core.framework.player.DataMap;
import dev.thedutchruben.core.framework.player.PlayerData;

import java.lang.reflect.Type;
import java.util.Map;

public class DataMapAdapter implements JsonSerializer<DataMap>, JsonDeserializer<DataMap> {

    @Override
    public DataMap deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        DataMap map = new DataMap();
        for (JsonElement x : element.getAsJsonArray()) {
            JsonObject jsonObject = x.getAsJsonObject();

            Class<?> checkableClass;
            try {
                checkableClass = Class.forName(jsonObject.get("type").getAsString());
            } catch (ClassNotFoundException e) {
                throw new JsonParseException(e);
            }

            if (!PlayerData.class.isAssignableFrom(checkableClass)) {
                throw new JsonParseException(checkableClass + " could not be cast to PlanetCraftData");
            }

            Class<? extends PlayerData> dataClass = (Class<? extends PlayerData>) checkableClass;
            PlayerData data = context.deserialize(jsonObject.get("data"), dataClass);
            map.put(dataClass, data);
        }
        return map;
    }

    @Override
    public JsonElement serialize(DataMap map, Type type, JsonSerializationContext context) {
        JsonArray array = new JsonArray();
        for (Map.Entry<Class<? extends PlayerData>, PlayerData> entry : map.entrySet()) {
            JsonObject object = new JsonObject();
            object.addProperty("type", entry.getKey().getName());
            object.add("data", context.serialize(entry.getValue()));
            array.add(object);
        }
        return array;
    }
}
