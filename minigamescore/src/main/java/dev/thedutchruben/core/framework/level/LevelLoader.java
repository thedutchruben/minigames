package dev.thedutchruben.core.framework.level;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import dev.thedutchruben.core.MiniGamesCore;
import io.netty.util.internal.ConcurrentSet;
import org.bson.Document;

import java.util.Set;
import java.util.concurrent.CompletableFuture;


public class LevelLoader {

    private final MongoCollection mongoCollection;
    private  final Gson gson = MiniGamesCore.getInstance().getMongoDb().getGson();
    public LevelLoader(){
        mongoCollection = MiniGamesCore.getInstance().getMongoDb().getMongoDatabase().getCollection("levels");
    }

    public Set<Level> loadLevels() {
        Set<Level> levels = new ConcurrentSet<>();
        mongoCollection.find().forEach((Block<Document>) document -> {
            Level level = gson.fromJson(document.toJson(), Level.class);
            levels.add(level);
        });
        return levels;
    }


    public CompletableFuture<Void> save(Level level) {
        return CompletableFuture.runAsync(() -> {
            Document document = Document.parse(gson.toJson(level, Level.class));
            mongoCollection.replaceOne(new BasicDBObject().append("_id", level.getLevel()), document, new UpdateOptions().upsert(true));
        }).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });
    }
}
