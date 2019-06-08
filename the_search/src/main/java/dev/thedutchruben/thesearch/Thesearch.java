package dev.thedutchruben.thesearch;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import dev.thedutchruben.core.framework.server.Game;
import dev.thedutchruben.core.framework.server.GameState;
import dev.thedutchruben.core.framework.server.GameType;
import dev.thedutchruben.minigamescore;
import dev.thedutchruben.thesearch.framework.map.Map;
import dev.thedutchruben.thesearch.modules.game.GameModule;
import dev.thedutchruben.thesearch.modules.player.PlayerModule;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.mongodb.client.model.Filters.eq;

public final class Thesearch extends JavaPlugin {
    private static Thesearch instance;
    private PlayerModule playerModule;
    private GameModule gameMode;
    private Map map;
    private  MongoCollection mongoCollection;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        new Game("thesearch", GameState.LOBBY, GameType.THE_SEARCH,1,10);
        mongoCollection = minigamescore.getInstance().getMongoDb().getMongoDatabase().getCollection("the_search");
        try {
            map = getMapDB().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        playerModule = new PlayerModule();
        gameMode = new GameModule();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for (Location head : getMap().getHeadLocations()) {
            head.getBlock().setType(Material.AIR);
        }
    }

    public PlayerModule getPlayerModule() {
        return playerModule;
    }

    public static Thesearch getInstance() {
        return instance;
    }





    public CompletableFuture<Map> getMapDB(){
        return CompletableFuture.supplyAsync(() -> {
            return mongoCollection.find(eq("_id", Bukkit.getWorlds().get(0).getName()));
        }).thenApply(documentIterator -> {
            Document document = (Document) documentIterator.first();
            Map map;
            if(document != null && !document.isEmpty()){
                map = minigamescore.getInstance().getMongoDb().getGson().fromJson(document.toJson(),Map.class);
            }else{
                map = new Map(new Location(Bukkit.getWorlds().get(0),0,0,0),new Location(Bukkit.getWorlds().get(0),0,0,0),new ArrayList<>(),10);
                save(map);
            }

            return map;
        });
    }


    public CompletableFuture<Void> save(Map map) {
        return CompletableFuture.runAsync(() -> {
            Document document = Document.parse(minigamescore.getInstance().getMongoDb().getGson().toJson(map, Map.class));
            mongoCollection.replaceOne(new BasicDBObject().append("_id", Bukkit.getWorlds().get(0).getName()), document, new UpdateOptions().upsert(true));
        }).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });
    }

    public GameModule getGameMode() {
        return gameMode;
    }

    public Map getMap() {
        return map;
    }
}
