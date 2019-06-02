package dev.thedutchruben.framework.database.adabters;

import com.google.gson.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Type;

public class ItemMetaAdapter implements JsonSerializer<ItemMeta>, JsonDeserializer<ItemMeta> {

    @Override
    public JsonElement serialize(ItemMeta src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("properties", context.serialize(src, src.getClass()));
        return result;
    }

    @Override
    public ItemMeta deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");

        try {
            String thePackage = "org.bukkit.inventory.meta.";
            return context.deserialize(element, Class.forName(thePackage + type));
        } catch (ClassNotFoundException exception) {
            throw new JsonParseException("Unknown element type: " + type, exception);
        }
    }
}
