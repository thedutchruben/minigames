package dev.thedutchruben.core.framework.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import dev.thedutchruben.core.MiniGamesCore;
import dev.thedutchruben.core.framework.database.adabters.DataMapAdapter;
import dev.thedutchruben.core.framework.database.adabters.ItemMetaAdapter;
import dev.thedutchruben.core.framework.database.adabters.LocationAdapter;
import dev.thedutchruben.core.framework.player.DataMap;
import dev.thedutchruben.core.utils.Config;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.logging.Level;



@Getter
/**
 * Database class for mongoDb
 */
public class MongoDb {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private final Gson gson;
    private final Config mongoConfig;

    /**
     * Connect Database
     */
    public MongoDb(){
        mongoConfig = new Config(MiniGamesCore.getInstance(),"mongo.yml");
        String host = mongoConfig.getConfiguration().getString("db.hostname","minigames-mongo-1");
        String databaseName = mongoConfig.getConfiguration().getString("db.dbname","minigames");
        int port = 27017;
        mongoClient = new MongoClient(new MongoClientURI("mongodb://" + host + ":" + port));
        mongoDatabase = mongoClient.getDatabase(databaseName);
        Bukkit.getServer().getLogger().log(Level.INFO, "MongoDB connected");
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ItemMetaAdapter.class, new ItemMetaAdapter());
        gsonBuilder.registerTypeAdapter(Location.class, new LocationAdapter());
        gsonBuilder.registerTypeAdapter(DataMap.class, new DataMapAdapter());
        gsonBuilder.setLongSerializationPolicy( LongSerializationPolicy.STRING );
        gson = gsonBuilder.create();
    }
}
