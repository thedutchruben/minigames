package dev.thedutchruben.core.framework.server;

import com.mongodb.BasicDBObject;
import com.mongodb.client.model.UpdateOptions;
import dev.thedutchruben.core.MiniGamesCore;
import org.bson.Document;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class GameLog {
    private String gameId;
    private String servername;
    private List<Log> logs;

    public GameLog(List<Log> data) {
        try {
        this.gameId = InetAddress.getLocalHost().getHostName() + new Date().toString();

            this.servername = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.logs = data;
        save();
    }

    public String getGameId() {
        return gameId;
    }

    public String getServername() {
        return servername;
    }

    public void setServername(String servername) {
        this.servername = servername;
    }

    public List<Log> getData() {
        return logs;
    }

    public void addEvent(Log log){
        logs.add(log);
        save();
    }

    public CompletableFuture<Void> save() {
        return CompletableFuture.runAsync(() -> {
            Document document = Document.parse(MiniGamesCore.getInstance().getMongoDb().getGson().toJson(this, GameLog.class));
            MiniGamesCore.getInstance().getMongoDb().getMongoDatabase().getCollection("game-log").replaceOne(new BasicDBObject().append("_id", this.gameId), document, new UpdateOptions().upsert(true));
        }).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });
    }
}
