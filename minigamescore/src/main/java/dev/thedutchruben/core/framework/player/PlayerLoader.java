package dev.thedutchruben.core.framework.player;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import dev.thedutchruben.core.minigamescore;
import org.bson.Document;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.mongodb.client.model.Filters.eq;

public class PlayerLoader {
    private final MongoCollection mongoCollection;
    private  final Gson gson = minigamescore.getInstance().getMongoDb().getGson();
    public PlayerLoader(){
        mongoCollection = minigamescore.getInstance().getMongoDb().getMongoDatabase().getCollection("player");
    }

    public CompletableFuture<MinigamesPlayer> getMinigamesPlayer(UUID uuid){
        return CompletableFuture.supplyAsync(() -> {
            return mongoCollection.find(eq("_id", uuid.toString()));
        }).thenApply(documentIterator -> {
            Document document = (Document) documentIterator.first();
            MinigamesPlayer minigamesPlayer;
            if(document != null && !document.isEmpty()){
                minigamesPlayer = gson.fromJson(document.toJson(),MinigamesPlayer.class);
            }else{
                minigamesPlayer = new MinigamesPlayer(uuid);
            }

            return minigamesPlayer;
        });
    }


    public CompletableFuture<Void> save(MinigamesPlayer player) {
        return CompletableFuture.runAsync(() -> {
            Document document = Document.parse(gson.toJson(player, MinigamesPlayer.class));
            mongoCollection.replaceOne(new BasicDBObject().append("_id", player.getUuid().toString()), document, new UpdateOptions().upsert(true));
        }).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });
    }

}
